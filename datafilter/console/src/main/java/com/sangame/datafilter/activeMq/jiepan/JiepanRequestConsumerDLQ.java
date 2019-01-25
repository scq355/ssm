package com.sangame.datafilter.activeMq.jiepan;

import com.alibaba.fastjson.JSON;
import com.sangame.datafilter.common.persistence.mapper.audit.FilterDataExceptionMapper;
import com.sangame.datafilter.common.persistence.mapper.audit.FilterJiepanAuditMapper;
import com.sangame.datafilter.common.persistence.model.audit.FilterDataException;
import com.sangame.datafilter.common.persistence.model.audit.FilterJiepanAudit;
import com.sangame.datafilter.constant.ConsoleConstant;
import com.sangame.datafilter.service.audit.BaseAuditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.listener.SessionAwareMessageListener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.util.Date;

/**
 * @author Mao Renwei
 * @Description
 * @date 2017/5/4.
 */
public class JiepanRequestConsumerDLQ implements SessionAwareMessageListener {

    private final static Logger LOG = LoggerFactory.getLogger(JiepanRequestConsumerDLQ.class);

    @Autowired
    FilterJiepanAuditMapper filterJiepanAuditMapper;
    @Autowired
    BaseAuditService baseAuditService;
    @Autowired
    FilterDataExceptionMapper filterDataExceptionMapper;
    @Autowired
    JiepanResponseProducer jiepanResponseProducer;

    public void onMessage(Message message, Session session) throws JMSException {
        try {
            if(message instanceof TextMessage){
                String json = ((TextMessage) message).getText();
                FilterJiepanAudit filterJiepanAudit = JSON.parseObject(json,FilterJiepanAudit.class);
                String result = baseAuditService.insert(filterJiepanAuditMapper,filterJiepanAudit);
                if(result != null){
                    jiepanResponseProducer.sendMessage(result,false);
                }
            }
        }catch (Exception e){
            LOG.error("jms消息处理出错",e);
            FilterDataException dataException = new FilterDataException();
            dataException.setMsg(e.getMessage());
            dataException.setJson(((TextMessage) message).getText());
            dataException.setCreatedAt(new Date());
            dataException.setStatus(ConsoleConstant.ExceptionStatus.UNTREATED.getValue());
            dataException.setProjectType(ConsoleConstant.ProjectType.JIEPAN.getValue());
            dataException.setDealType(ConsoleConstant.ExceptionDealType.INSERT.getValue());
            filterDataExceptionMapper.insert(dataException);
        }
    }
}

package com.sangame.datafilter.activeMq.comment;

import com.alibaba.fastjson.JSON;
import com.sangame.datafilter.common.persistence.mapper.audit.FilterCommentAuditMapper;
import com.sangame.datafilter.common.persistence.mapper.audit.FilterDataExceptionMapper;
import com.sangame.datafilter.common.persistence.model.audit.FilterCommentAudit;
import com.sangame.datafilter.common.persistence.model.audit.FilterDataException;
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
public class CommentRequestConsumerDLQ implements SessionAwareMessageListener {

    private final static Logger LOG = LoggerFactory.getLogger(CommentRequestConsumerDLQ.class);

    @Autowired
    FilterCommentAuditMapper filterCommentAuditMapper;
    @Autowired
    BaseAuditService baseAuditService;
    @Autowired
    FilterDataExceptionMapper filterDataExceptionMapper;
    @Autowired
    CommentResponseProducer commentResponseProducer;

    public void onMessage(Message message, Session session) throws JMSException {
        try {
            if(message instanceof TextMessage){
                String json = ((TextMessage) message).getText();
                FilterCommentAudit filterCommentAudit = JSON.parseObject(json,FilterCommentAudit.class);
                String result = baseAuditService.insert(filterCommentAuditMapper,filterCommentAudit);
                if(result != null){
                    commentResponseProducer.sendMessage(result,false);
                }
            }
        }catch (Exception e){
            LOG.error("jms消息处理出错",e);
            FilterDataException dataException = new FilterDataException();
            dataException.setMsg(e.getMessage());
            dataException.setJson(((TextMessage) message).getText());
            dataException.setCreatedAt(new Date());
            dataException.setStatus(ConsoleConstant.ExceptionStatus.UNTREATED.getValue());
            dataException.setProjectType(ConsoleConstant.ProjectType.COMMENT.getValue());
            dataException.setDealType(ConsoleConstant.ExceptionDealType.INSERT.getValue());
            filterDataExceptionMapper.insert(dataException);
        }
    }
}

package com.sangame.datafilter.activeMq.jiepan;

import com.alibaba.fastjson.JSON;
import com.sangame.datafilter.common.persistence.mapper.audit.FilterJiepanAuditMapper;
import com.sangame.datafilter.common.persistence.model.audit.FilterJiepanAudit;
import com.sangame.datafilter.service.audit.BaseAuditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.listener.SessionAwareMessageListener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * @author Mao Renwei
 * @Description
 * @date 2017/5/4.
 */
public class JiepanRequestConsumer implements SessionAwareMessageListener {

    private final static Logger LOG = LoggerFactory.getLogger(JiepanRequestConsumer.class);

    @Autowired
    BaseAuditService baseAuditService;
    @Autowired
    FilterJiepanAuditMapper filterJiepanAuditMapper;
    @Autowired
    JiepanResponseProducer jiepanResponseProducer;

    @Override
    public void onMessage(Message message, Session session) throws JMSException{

            if(message instanceof TextMessage){
                String json = ((TextMessage) message).getText();
                FilterJiepanAudit filterJiepanAudit = JSON.parseObject(json,FilterJiepanAudit.class);
                String result = baseAuditService.insert(filterJiepanAuditMapper,filterJiepanAudit);
                if(result != null){
                    jiepanResponseProducer.sendMessage(result,true);
                }
            }
    }
}

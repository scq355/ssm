package com.sangame.datafilter.activeMq.comment;

import com.alibaba.fastjson.JSON;
import com.sangame.datafilter.common.persistence.mapper.audit.FilterCommentAuditMapper;
import com.sangame.datafilter.common.persistence.model.audit.FilterCommentAudit;
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
public class CommentRequestConsumer implements SessionAwareMessageListener {

    private final static Logger LOG = LoggerFactory.getLogger(CommentRequestConsumer.class);
    @Autowired
    BaseAuditService baseAuditService;
    @Autowired
    FilterCommentAuditMapper filterCommentAuditMapper;
    @Autowired
    CommentResponseProducer commentResponseProducer;

    @Override
    public void onMessage(Message message, Session session) throws JMSException{

            if(message instanceof TextMessage){
                String json = ((TextMessage) message).getText();
                FilterCommentAudit filterCommentAudit = JSON.parseObject(json,FilterCommentAudit.class);
                String result = baseAuditService.insert(filterCommentAuditMapper,filterCommentAudit);
                if(result != null){
                    commentResponseProducer.sendMessage(result,true);
                }
            }
    }
}

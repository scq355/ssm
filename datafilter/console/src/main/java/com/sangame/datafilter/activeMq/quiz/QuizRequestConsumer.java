package com.sangame.datafilter.activeMq.quiz;

import com.alibaba.fastjson.JSON;
import com.sangame.datafilter.common.persistence.mapper.audit.FilterQuizAuditMapper;
import com.sangame.datafilter.common.persistence.model.audit.FilterQuizAudit;
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
public class QuizRequestConsumer implements SessionAwareMessageListener {

    private final static Logger LOG = LoggerFactory.getLogger(QuizRequestConsumer.class);

    @Autowired
    BaseAuditService baseAuditService;
    @Autowired
    FilterQuizAuditMapper filterQuizAuditMapper;
    @Autowired
    QuizResponseProducer quizResponseProducer;

    @Override
    public void onMessage(Message message, Session session) throws JMSException{

            if(message instanceof TextMessage){
                String json = ((TextMessage) message).getText();
                FilterQuizAudit filterQuizAudit = JSON.parseObject(json,FilterQuizAudit.class);
                String result = baseAuditService.insert(filterQuizAuditMapper,filterQuizAudit);
                if(result != null){
                    quizResponseProducer.sendMessage(result,true);
                }
            }
    }
}

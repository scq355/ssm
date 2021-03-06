package com.sangame.datafilter.activeMq.quiz;

import com.alibaba.fastjson.JSON;
import com.sangame.datafilter.common.persistence.mapper.audit.FilterDataExceptionMapper;
import com.sangame.datafilter.common.persistence.mapper.audit.FilterQuizAuditMapper;
import com.sangame.datafilter.common.persistence.model.audit.FilterDataException;
import com.sangame.datafilter.common.persistence.model.audit.FilterQuizAudit;
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
public class QuizRequestConsumerDLQ implements SessionAwareMessageListener {

    private final static Logger LOG = LoggerFactory.getLogger(QuizRequestConsumerDLQ.class);

    @Autowired
    FilterQuizAuditMapper filterQuizAuditMapper;
    @Autowired
    BaseAuditService baseAuditService;
    @Autowired
    FilterDataExceptionMapper filterDataExceptionMapper;
    @Autowired
    QuizResponseProducer quizResponseProducer;

    public void onMessage(Message message, Session session) throws JMSException {
        try {
            if(message instanceof TextMessage){
                String json = ((TextMessage) message).getText();
                FilterQuizAudit filterQuizAudit = JSON.parseObject(json,FilterQuizAudit.class);
                String result = baseAuditService.insert(filterQuizAuditMapper,filterQuizAudit);
                if(result != null){
                    quizResponseProducer.sendMessage(result,false);
                }
            }
        }catch (Exception e){
            LOG.error("jms消息处理出错",e);
            FilterDataException dataException = new FilterDataException();
            dataException.setMsg(e.getMessage());
            dataException.setJson(((TextMessage) message).getText());
            dataException.setCreatedAt(new Date());
            dataException.setStatus(ConsoleConstant.ExceptionStatus.UNTREATED.getValue());
            dataException.setProjectType(ConsoleConstant.ProjectType.QUIZ.getValue());
            dataException.setDealType(ConsoleConstant.ExceptionDealType.INSERT.getValue());
            filterDataExceptionMapper.insert(dataException);
        }
    }
}

package com.sangame.datafilter.activeMq;

import com.sangame.datafilter.common.persistence.mapper.audit.FilterDataExceptionMapper;
import com.sangame.datafilter.common.persistence.model.audit.FilterDataException;
import com.sangame.datafilter.constant.ConsoleConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.Date;

/**
 * @author Mao Renwei
 * @Description
 * @date 2017/5/9.
 */
@Component
public class BaseResponseProducer {

    private static final Logger LOG = LoggerFactory.getLogger(BaseResponseProducer.class);

    @Resource
    private JmsTemplate jmsTemplate;
    @Autowired
    private FilterDataExceptionMapper filterDataExceptionMapper;

    public void sendMessage(Destination destination,final String message,int projectType,boolean exceptionDealFlag) {
        try {
            jmsTemplate.send(destination, new MessageCreator() {
                public Message createMessage(Session session) throws JMSException {
                    return session.createTextMessage(message);
                }
            });
        }catch (Exception e){
            // 消息发送失败，记录下消息
            LOG.error("消息发送失败",e);
            if(exceptionDealFlag){
                FilterDataException dataException = new FilterDataException();
                dataException.setMsg(e.getMessage());
                dataException.setJson(message);
                dataException.setCreatedAt(new Date());
                dataException.setStatus(ConsoleConstant.ExceptionStatus.UNTREATED.getValue());
                dataException.setProjectType(projectType);
                dataException.setDealType(ConsoleConstant.ExceptionDealType.SEND.getValue());
                filterDataExceptionMapper.insert(dataException);
            }
        }
    }
}

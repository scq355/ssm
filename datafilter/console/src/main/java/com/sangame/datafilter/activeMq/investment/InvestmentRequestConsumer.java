package com.sangame.datafilter.activeMq.investment;

import com.alibaba.fastjson.JSON;
import com.sangame.datafilter.common.persistence.mapper.audit.FilterInvestmentAuditMapper;
import com.sangame.datafilter.common.persistence.model.audit.FilterInvestmentAudit;
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
public class InvestmentRequestConsumer implements SessionAwareMessageListener {

    private final static Logger LOG = LoggerFactory.getLogger(InvestmentRequestConsumer.class);

    @Autowired
    BaseAuditService baseAuditService;
    @Autowired
    FilterInvestmentAuditMapper filterInvestmentAuditMapper;
    @Autowired
    InvestmentResponseProducer investmentResponseProducer;

    @Override
    public void onMessage(Message message, Session session) throws JMSException{

            if(message instanceof TextMessage){
                String json = ((TextMessage) message).getText();
                FilterInvestmentAudit filterInvestmentAudit = JSON.parseObject(json,FilterInvestmentAudit.class);
                String result = baseAuditService.insert(filterInvestmentAuditMapper,filterInvestmentAudit);
                if(result != null){
                    investmentResponseProducer.sendMessage(result,true);
                }
            }
    }
}

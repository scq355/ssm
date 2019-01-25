package com.sangame.datafilter.activeMq.market;

import com.alibaba.fastjson.JSON;
import com.sangame.datafilter.common.persistence.mapper.audit.FilterMarketAuditMapper;
import com.sangame.datafilter.common.persistence.model.audit.FilterMarketAudit;
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
public class MarketRequestConsumer implements SessionAwareMessageListener {

    private final static Logger LOG = LoggerFactory.getLogger(MarketRequestConsumer.class);

    @Autowired
    BaseAuditService baseAuditService;
    @Autowired
    FilterMarketAuditMapper filterMarketAuditMapper;
    @Autowired
    MarketResponseProducer marketResponseProducer;

    @Override
    public void onMessage(Message message, Session session) throws JMSException{

            if(message instanceof TextMessage){
                String json = ((TextMessage) message).getText();
                FilterMarketAudit filterMarketAudit = JSON.parseObject(json,FilterMarketAudit.class);
                String result = baseAuditService.insert(filterMarketAuditMapper,filterMarketAudit);
                if(result != null){
                    marketResponseProducer.sendMessage(result,true);
                }
            }
    }
}

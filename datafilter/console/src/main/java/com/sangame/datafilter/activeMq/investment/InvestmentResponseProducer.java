package com.sangame.datafilter.activeMq.investment;

import com.sangame.datafilter.activeMq.BaseResponseProducer;
import com.sangame.datafilter.constant.ConsoleConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.jms.Destination;

/**
 * @author Mao Renwei
 * @Description 评论消息返回  （生产者）
 * @date 2017/5/4.
 */
@Component
public class InvestmentResponseProducer extends BaseResponseProducer{

    @Autowired
    @Qualifier("investmentResponseQueueDestination")
    Destination destination;

    @Async
    public void sendMessage(final String message, boolean exceptionDealFlag) {
       super.sendMessage(destination,message, ConsoleConstant.ProjectType.INVESTMENT.getValue(),exceptionDealFlag);
    }
}

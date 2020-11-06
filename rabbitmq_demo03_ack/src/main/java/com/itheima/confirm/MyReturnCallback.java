package com.itheima.confirm;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/***
 * @ClassName MyReturnCallback
 * @Description 回调函数
 * @version 1.0.0
 * @author ayuan
 * @createTime 2020/11/05 18:09:00
 */
@Component
public class MyReturnCallback implements RabbitTemplate.ReturnCallback {

    /***
     * @title returnedMessage
     * @description
     * @param message 消息信息
	 * @param replyCode 退回的状态码
	 * @param replyText 退回的信息
	 * @param exchange 交换机
	 * @param routingKey 路由key
     * @return void
     * @author ayuan
     * @updateTime 2020/11/6 22:25
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.out.println("退回的消息是："+new String(message.getBody()));
        System.out.println("退回的replyCode是："+replyCode);
        System.out.println("退回的replyText是："+replyText);
        System.out.println("退回的exchange是："+exchange);
        System.out.println("退回的routingKey是："+routingKey);
    }
}
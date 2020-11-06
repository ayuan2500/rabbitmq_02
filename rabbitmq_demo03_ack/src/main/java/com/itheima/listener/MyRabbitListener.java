package com.itheima.listener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/***
 * @ClassName MyRabbitListener
 * @Description 描述
 * @version 1.0.0
 * @author ayuan
 * @createTime 2020/11/06 23:17:00
 */
@Component
@RabbitListener(queues = "queue_demo01")
public class MyRabbitListener {

    @RabbitHandler
    public void msg(Message message, Channel channel, String msg) {
        //接收消息
        System.out.println("消费Duang接收消息：" + msg);
        try {
            //处理本地业务
            System.out.println("处理本地业务开始======start======");
            Thread.sleep(2000);
            int i = 1 / 0;
            System.out.println("处理本地业务结束======end======");
            //签收消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            e.printStackTrace();
            //如果出现异常，则拒绝消息 可以重回队列 也可以丢弃 可以根据业务场景来
            try {
                if (message.getMessageProperties().getRedelivered()) {
                    //消息已经重新投递，不需要再次投递
                    System.out.println("已经投递一次了");
                } else {
                    //第三个参数：设置是否重回队列
                    channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
                }
                //channel.basicReject(message.getMessageProperties().getDeliveryTag(),false);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}

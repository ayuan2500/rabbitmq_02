package com.itheima.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * @ClassName TestController
 * @Description 发送消息
 * @version 1.0.0
 * @author ayuan
 * @createTime 2020/11/05 18:09:00
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private RabbitTemplate.ReturnCallback myReturnCallback;

    @RequestMapping("/send2")
    public String send2() {
        //设置回调函数
        rabbitTemplate.setReturnCallback(myReturnCallback);
        //发送消息
        rabbitTemplate.convertAndSend("exchange_direct_demo01", "item.insert1234", "hello insert");
        return "ok";
    }
}

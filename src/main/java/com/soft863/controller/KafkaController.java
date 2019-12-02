package com.soft863.controller;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @description:
 * @author: wangchaojie
 * @create: 2019-11-20 15:18
 **/
@Controller
@RequestMapping("/kafka")
public class KafkaController {
   /* @Resource
    private KafkaTemplate<String,Object> kafkaTemplate;

    @GetMapping("/send/{message}")
    @ResponseBody
    public String send(@PathVariable String message){
        kafkaTemplate.send("testwcj",message);
        return "success";
    }


    @KafkaListener(topics = "testwcj")
    public void onMessage(String message){
        //insertIntoDb(message);//这里为插入数据库代码
        System.out.println("收到消息****："+message);
    }*/
}

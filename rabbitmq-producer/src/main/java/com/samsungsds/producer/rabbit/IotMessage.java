package com.samsungsds.producer.rabbit;

import com.samsungsds.model.Message;

import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.cloud.stream.annotation.EnableBinding;
 import org.springframework.messaging.support.MessageBuilder;
 import org.springframework.stereotype.Component;

 import org.apache.logging.log4j.LogManager;
 import org.apache.logging.log4j.Logger;

 @Component
 @EnableBinding(IotMessageDeliver.class)
 public class IotMessage {

    private static final Logger logger = LogManager.getLogger(IotMessage.class); 

     @Autowired
     private IotMessageDeliver iotEventDeliver;

     public void pubMessage(int num) {
         Message model = new Message();
         logger.info("generate data :" + num);  // 로그로 호출시간 확인
         for(int i = 0; i< num; i++)
         {
            model.setDate();
            iotEventDeliver.output().send(MessageBuilder.withPayload(model).build());
         }
     }
 }

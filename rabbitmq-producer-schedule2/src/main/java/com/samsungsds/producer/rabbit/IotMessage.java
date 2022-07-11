package com.samsungsds.producer.rabbit;

import com.samsungsds.model.Message;

import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.cloud.stream.annotation.EnableBinding;
 import org.springframework.messaging.support.MessageBuilder;
 import org.springframework.stereotype.Component;

 @Component
 @EnableBinding(IotMessageDeliver.class)
 public class IotMessage {

     @Autowired
     private IotMessageDeliver iotEventDeliver;

     public void pubMessage(int num) {
         Message model = new Message();
         
         for(int i = 0; i< num; i++)
         {
            model.setDate();
            iotEventDeliver.output().send(MessageBuilder.withPayload(model).build());
         }
     }
 }

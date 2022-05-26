 package com.samsungsds.producer.rabbit;

 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;

 @Service
 public class IotService {

     @Autowired
     private IotMessage publishMsg;

     public void pubMessage(int num) {
         publishMsg.pubMessage(num);
     }
 }

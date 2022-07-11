 package com.samsungsds.producer.rabbit;

 import org.springframework.cloud.stream.annotation.Output;
 import org.springframework.messaging.MessageChannel;

 public interface IotMessageDeliver {
   String Name = "iot.event";

   @Output(IotMessageDeliver.Name)
   MessageChannel output();
 }

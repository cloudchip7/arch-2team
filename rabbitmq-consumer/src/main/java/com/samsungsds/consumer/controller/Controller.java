package com.samsungsds.consumer.controller;

import com.samsungsds.model.IotDataModel;

import java.util.Date;

import com.samsungsds.consumer.receiver.IotEventReceiver;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@RestController
@RequestMapping(value = "/api")
public class Controller {

    private static final Logger logger = LogManager.getLogger(Controller.class);
    // private long total = 0;
    // private long current = 0;

    public Controller() { }

    @GetMapping(value = "/status")
    public @ResponseBody ResponseEntity getStatus() {
        return  ResponseEntity.ok("");
    }

    @GetMapping(value = "/reset")
    public @ResponseBody ResponseEntity reset() {
        total = 0;
        return  ResponseEntity.ok("");
    }

    private long t1_last = new Date().getTime();
    private long t2_last = 0;    

    private long total = 0;
    private long count = 0;

    @StreamListener(IotEventReceiver.INPUT1)
    public void received1(IotDataModel model) {
        total++;
        count++; 
        // try {
        //     Thread.sleep(10);
        // }
        // catch(Exception e) { }
    }

    @Async
    @Scheduled(fixedRate = 1000)
    public void scheduleFixedDelayTask2() throws InterruptedException {
        logger.info("Current TPS : " + count + " Total Data : " + total);   //  1초당 수신 수 : TPS
        count = 0;
    }
}
 
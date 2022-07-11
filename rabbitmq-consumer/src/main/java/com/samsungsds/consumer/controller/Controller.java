package com.samsungsds.consumer.controller;

import com.samsungsds.model.IotDataModel;

import java.util.Date;

import com.samsungsds.consumer.receiver.IotEventReceiver;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping(value = "/api")
public class Controller {

    private static final Logger logger = LogManager.getLogger(Controller.class);

    private long total = 0;
    private long p1 = 0;
    private long p2 = 0;
    private long p3 = 0;

    public Controller() { }

    @GetMapping(value = "/status")
    public @ResponseBody ResponseEntity produceRabbit() {
        long gap = end - start;
        return  ResponseEntity.ok("total : " + total + "gap : " + gap);
    }

    private long start = 0;
    private long end = 0; 

    @GetMapping(value = "/reset")
    public @ResponseBody ResponseEntity produceRabbit2() {
        
        start = 0;
        end = 0;
        total = 0;
        return  ResponseEntity.ok("RESET");
    }

    private long t1_last = new Date().getTime();
    private long t2_last = 0;    

    @StreamListener(IotEventReceiver.INPUT1)
    public void received1(IotDataModel model) {


        Date current = new Date();

        long gap = current.getTime() - t1_last;
        t1_last = current.getTime();
        total++;

        if(start == 0)
            start = current.getTime();
        end = current.getTime();

        logger.info("R1 : " + gap);

    }

    @StreamListener(IotEventReceiver.INPUT2)
    public void received2(IotDataModel model) {
        Date current = new Date();
        t2_last = current.getTime() - t2_last;
 
        logger.info("R2 : " + current);
    }
}
 
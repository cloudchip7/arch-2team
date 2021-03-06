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
        
        StringBuilder sb = new StringBuilder();
        sb.append("total : " + total + "    ");
        sb.append("0~50 : " + p1 + "    ");
        sb.append("51~100 : " + p2 + "    ");
        sb.append("101~ : " + p3 + "\n");
        return  ResponseEntity.ok(sb.toString());
    }

    @StreamListener(IotEventReceiver.INPUT1)
    public void received1(IotDataModel model) {
        long current = new Date().getTime();
        Date d = new Date(model.date);
        long gap = current - model.date;

        total++;

        if(gap < 51) p1++;
        else if(gap < 101) p2++;
        else p3++;
        
        logger.info("R1 : " + gap + "model.date : "+ d);
    }

    @StreamListener(IotEventReceiver.INPUT2)
    public void received2(IotDataModel model) {
        long current = new Date().getTime();
        Date d = new Date(model.date);
        long gap = current - model.date;

        total++;

        if(gap < 51) p1++;
        else if(gap < 101) p2++;
        else p3++;
        
        logger.info("R2 : " + gap + "model.date : "+ d);
    }
}
 
package com.samsungsds.producer.controller;

import com.samsungsds.model.Message;
import com.samsungsds.producer.rabbit.IotService;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class ExecutionController {

    private final IotService iotService;
    private int sendNum = 1;

    public ExecutionController(final IotService iotService) {
         this.iotService = iotService;
    }

    @GetMapping(value = "/{num}")
    public @ResponseBody ResponseEntity produceRabbit(@PathVariable("num") Integer num) {
        sendNum = num;
        return  ResponseEntity.ok("OK");
    }

    @Async
    @Scheduled(fixedRate = 1000) 
    public void scheduleFixedDelayTask2() throws InterruptedException {
        iotService.pubMessage(sendNum);
    }
}
 
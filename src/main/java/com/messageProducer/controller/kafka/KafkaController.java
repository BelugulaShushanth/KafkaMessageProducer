package com.messageProducer.controller.kafka;

import com.messageProducer.service.kafka.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @GetMapping(value = "/kafka/sendmessage/{message}")
    public String sendMessage(@PathVariable("message") String message){
        String response = kafkaProducerService.sendKafkaMessage(message);
        return response;
    }
}

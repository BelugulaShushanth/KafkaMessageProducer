package com.messageProducer.service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class KafkaProducerService {

    @Value("${spring.kafka.topic-name}")
    private String topic_name;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    public String sendKafkaMessage(String message){
        kafkaTemplate.send(topic_name, message);
        return "Message Sent to kafka";
    }

    @PostConstruct
    public void sendKafkaMessages() throws InterruptedException {
        String message = "";
        int i = 0;
        while(true) {
            message = "Shushanth: "+i;
            kafkaTemplate.send(topic_name, message);
            System.out.println("Sent message: "+message);
            Thread.sleep(20);
            i++;
            if(i==100000){break;}
        }
    }
}

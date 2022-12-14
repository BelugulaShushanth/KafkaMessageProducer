package com.messageProducer.configuration.kafka;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class KafkaProducerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrap_servers;
    @Value("${spring.kafka.key-serializer}")
    private String key_serializer;
    @Value("${spring.kafka.value-serializer}")
    private String value_serializer;

    @Bean
    public ProducerFactory<String,String> getProducerFactory(){
        Map<String,Object> properties = new HashMap();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap_servers);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,key_serializer);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,value_serializer);
        return new DefaultKafkaProducerFactory<String,String>(properties);
    }

    @Bean
    public KafkaTemplate<String,String> kafkaTemplate(){
        return new KafkaTemplate<>(getProducerFactory());
    }
}

package com.mindata.riu.infrastructure.out.kafka.config;

import com.mindata.riu.infrastructure.out.kafka.dto.SearchEvent;
import com.mindata.riu.infrastructure.out.kafka.dto.SearchKey;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.kafka.autoconfigure.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaConfig {

    @Bean
    public ProducerFactory<SearchKey, SearchEvent> producerFactory(KafkaProperties kafkaProperties){
        return new DefaultKafkaProducerFactory<>(kafkaProperties.buildProducerProperties());
    }

    @Bean
    public KafkaTemplate<SearchKey, SearchEvent> kafkaTemplate(ProducerFactory<SearchKey, SearchEvent> producerFactory){
        return new KafkaTemplate<>(producerFactory);
    }

    @Bean
    public NewTopic searchesTopic(){
        return TopicBuilder
            .name(KafkaConstants.OUTPUT_TOPIC)
            .partitions(1)
            .replicas(1)
            .build();
    }

}

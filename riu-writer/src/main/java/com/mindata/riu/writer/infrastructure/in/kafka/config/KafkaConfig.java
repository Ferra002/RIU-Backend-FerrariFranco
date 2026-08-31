package com.mindata.riu.writer.infrastructure.in.kafka.config;

import com.mindata.riu.writer.domain.exception.search.CheckInAfterCheckOutException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.TopicPartition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

@Slf4j
@Configuration
public class KafkaConfig {

    @Bean
    public DefaultErrorHandler errorHandler(KafkaTemplate<Object, Object> kafkaTemplate){
        var recover = new DeadLetterPublishingRecoverer(
            kafkaTemplate,
            (consumerRecord, ex) -> {
                log.error(
                    "Sending message to DLQ. Key: '{}'. Value: '{}'. Exception: ''",
                    consumerRecord.key(),
                    consumerRecord.value(),
                    ex
                );

                return new TopicPartition(
                    KafkaConstants.DQL_TOPIC,
                    consumerRecord.partition()
                );
            }
        );

        var errorHandler = new DefaultErrorHandler(
            recover,
            new FixedBackOff(1000L, 2L)
        );

        errorHandler.addNotRetryableExceptions(
            IllegalArgumentException.class,
            ConstraintViolationException.class,
            CheckInAfterCheckOutException.class
        );

        return errorHandler;
    }

}

package com.mindata.riu.writer.infrastructure.in.kafka.config;

import com.mindata.riu.writer.domain.exception.search.SearchException;
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
            (record, ex) -> {
                log.error(
                    "Sending message to DLQ. Key: '{}'. Value: '{}'. Exception: ''",
                    record.key(),
                    record.value(),
                    ex
                );

                return new TopicPartition(
                    KafkaConstants.DQL_TOPIC,
                    record.partition()
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
            SearchException.class
        );

        return errorHandler;
    }

}

package com.mindata.riu.writer.infrastructure.in.kafka.config;

import com.mindata.riu.writer.domain.exception.search.CheckInAfterCheckOutException;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.listener.MessageListenerContainer;

import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class KafkaConfigTest {

    @Mock
    private KafkaTemplate<Object, Object> kafkaTemplate;

    @Mock
    private Consumer<Object, Object> consumer;

    @Mock
    private MessageListenerContainer messageListenerContainer;

    @InjectMocks
    private KafkaConfig kafkaConfig;

    @Test
    void testErrorHandler() {
        DefaultErrorHandler errorHandler = kafkaConfig.errorHandler(kafkaTemplate);

        assertNotNull(errorHandler);
    }

    @Test
    void testSendMessageToDlq(){
        DefaultErrorHandler errorHandler = kafkaConfig.errorHandler(kafkaTemplate);

        ConsumerRecord<Object, Object> consumerRecord = new ConsumerRecord<>(
            "topic",
            1,
            0L,
            "key",
            "value"
        );

        when(kafkaTemplate.send(any(ProducerRecord.class)))
            .thenReturn(CompletableFuture.completedFuture(null));

        errorHandler.handleOne(
            new CheckInAfterCheckOutException(LocalDate.MAX, LocalDate.MIN),
            consumerRecord,
            consumer,
            messageListenerContainer
        );

        verify(kafkaTemplate).send(any(ProducerRecord.class));
    }
}
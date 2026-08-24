package com.mindata.riu.writer.infrastructure.in.kafka.consumer;

import com.mindata.riu.writer.application.port.in.ProcessSearchUseCase;
import com.mindata.riu.writer.infrastructure.in.kafka.config.KafkaConstants;
import com.mindata.riu.writer.infrastructure.in.kafka.dto.SearchEvent;
import com.mindata.riu.writer.infrastructure.in.kafka.dto.SearchKey;
import com.mindata.riu.writer.infrastructure.in.kafka.mapper.KafkaSearchMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaSearchEventConsumer {

    private final ProcessSearchUseCase processSearchUseCase;
    private final KafkaSearchMapper mapper;

    @KafkaListener(topics = KafkaConstants.INPUT_TOPIC)
    public void process(
        @Header(KafkaHeaders.RECEIVED_KEY) SearchKey key,
        @Payload SearchEvent value
    ){
        log.info("Kafka message received. Key: '{}'. Value: '{}'", key, value);

        processSearchUseCase.process(mapper.toCriteria(key, value));
    }

}

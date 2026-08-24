package com.mindata.riu.searcher.infrastructure.out.kafka.producer;

import com.mindata.riu.searcher.domain.model.SearchCriteria;
import com.mindata.riu.searcher.factory.TestClassBuilder;
import com.mindata.riu.searcher.infrastructure.out.kafka.config.KafkaConstants;
import com.mindata.riu.searcher.infrastructure.out.kafka.dto.SearchEvent;
import com.mindata.riu.searcher.infrastructure.out.kafka.dto.SearchKey;
import com.mindata.riu.searcher.infrastructure.out.kafka.mapper.KafkaSearchMapper;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class KafkaSearchEventPublisherTest {

    @Mock
    KafkaTemplate<SearchKey, SearchEvent> kafkaTemplate;

    @Mock
    KafkaSearchMapper mapper;

    @Mock
    private SendResult<SearchKey, SearchEvent> sendResult;

    @Mock
    private RecordMetadata recordMetadata;

    @InjectMocks
    KafkaSearchEventPublisher publisher;

    @Test
    void publish() {
        String searchId = "search-id";
        SearchCriteria criteria = TestClassBuilder.SEARCH_CRITERIA;

        SearchKey key = new SearchKey(searchId);
        SearchEvent event = TestClassBuilder.SEARCH_EVENT;

        when(mapper.toEvent(criteria)).thenReturn(event);
        when(kafkaTemplate.send(KafkaConstants.OUTPUT_TOPIC, key, event))
            .thenReturn(CompletableFuture.completedFuture(sendResult));
        when(sendResult.getRecordMetadata()).thenReturn(recordMetadata);
        when(recordMetadata.topic()).thenReturn(KafkaConstants.OUTPUT_TOPIC);
        when(recordMetadata.partition()).thenReturn(1);
        when(recordMetadata.offset()).thenReturn(2L);

        publisher.publish(searchId, criteria);

        verify(mapper).toEvent(criteria);
        verify(kafkaTemplate).send(KafkaConstants.OUTPUT_TOPIC, key, event);
    }

    @Test
    void publishFail() {
        String searchId = "search-id";
        SearchCriteria criteria = TestClassBuilder.SEARCH_CRITERIA;

        SearchKey key = new SearchKey(searchId);
        SearchEvent event = TestClassBuilder.SEARCH_EVENT;

        when(mapper.toEvent(criteria)).thenReturn(event);
        when(kafkaTemplate.send(KafkaConstants.OUTPUT_TOPIC, key, event))
            .thenReturn(CompletableFuture.failedFuture(new RuntimeException("Dummy error")));

        assertDoesNotThrow(() -> publisher.publish(searchId, criteria));

        verify(mapper).toEvent(criteria);
        verify(kafkaTemplate).send(KafkaConstants.OUTPUT_TOPIC, key, event);
    }

}
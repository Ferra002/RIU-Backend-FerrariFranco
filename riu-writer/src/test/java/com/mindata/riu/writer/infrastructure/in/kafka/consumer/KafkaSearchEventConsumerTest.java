package com.mindata.riu.writer.infrastructure.in.kafka.consumer;

import com.mindata.riu.writer.application.port.in.ProcessSearchUseCase;
import com.mindata.riu.writer.domain.dto.SearchCriteria;
import com.mindata.riu.writer.factory.TestClassBuilder;
import com.mindata.riu.writer.infrastructure.in.kafka.dto.SearchEvent;
import com.mindata.riu.writer.infrastructure.in.kafka.dto.SearchKey;
import com.mindata.riu.writer.infrastructure.in.kafka.mapper.KafkaSearchMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class KafkaSearchEventConsumerTest {

    @Mock
    private ProcessSearchUseCase processSearchUseCase;

    @Mock
    private KafkaSearchMapper mapper;

    @InjectMocks
    private KafkaSearchEventConsumer consumer;

    @Test
    void process() {
        SearchKey key = TestClassBuilder.SEARCH_KEY;
        SearchEvent event = TestClassBuilder.SEARCH_EVENT;
        SearchCriteria criteria = TestClassBuilder.SEARCH_CRITERIA;

        when(mapper.toCriteria(key, event)).thenReturn(criteria);

        consumer.process(key, event);

        verify(mapper).toCriteria(key, event);
        verify(processSearchUseCase).process(criteria);
    }
}
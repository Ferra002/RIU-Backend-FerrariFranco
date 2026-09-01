package com.mindata.riu.searcher.infrastructure.out.kafka.producer;

import com.mindata.riu.searcher.application.port.out.event.SearchEventPublisher;
import com.mindata.riu.searcher.domain.model.SearchCriteria;
import com.mindata.riu.searcher.infrastructure.out.kafka.config.KafkaConstants;
import com.mindata.riu.searcher.infrastructure.out.kafka.dto.SearchEvent;
import com.mindata.riu.searcher.infrastructure.out.kafka.dto.SearchKey;
import com.mindata.riu.searcher.infrastructure.out.kafka.mapper.KafkaSearchMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaSearchEventPublisher implements SearchEventPublisher {

    private final KafkaTemplate<SearchKey, SearchEvent> kafkaTemplate;
    private final KafkaSearchMapper mapper;

    @Override
    public void publish(String searchId, SearchCriteria searchCriteria) {
        SearchKey key = new SearchKey(searchId);
        SearchEvent event = mapper.toEvent(searchCriteria);

        kafkaTemplate
            .send(KafkaConstants.OUTPUT_TOPIC, key, event)
            .whenComplete((result, ex) -> {
                if(ex != null) {
                    log.error("Unable to sent Kafka message: '{}'. Key: '{}'. Value: '{}'", ex.getMessage(), key, event);
                    return;
                }

                var metadata = result.getRecordMetadata();

                log.info("Sent Kafka message. Topic: '{}'. Partition: '{}'. Offset: '{}'. Key: '{}'",
                    metadata.topic(),
                    metadata.partition(),
                    metadata.offset(),
                    key
                );
            });
    }

}

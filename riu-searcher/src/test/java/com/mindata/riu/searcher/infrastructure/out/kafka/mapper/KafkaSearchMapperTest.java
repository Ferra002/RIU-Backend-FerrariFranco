package com.mindata.riu.searcher.infrastructure.out.kafka.mapper;

import com.mindata.riu.searcher.domain.model.SearchCriteria;
import com.mindata.riu.searcher.factory.TestClassBuilder;
import com.mindata.riu.searcher.infrastructure.out.kafka.dto.SearchEvent;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class KafkaSearchMapperTest {

    KafkaSearchMapper mapper = Mappers.getMapper(KafkaSearchMapper.class);

    @Test
    void toEvent() {
        SearchCriteria criteria = TestClassBuilder.SEARCH_CRITERIA;
        SearchEvent result = mapper.toEvent(criteria);

        assertAll(
            () -> assertNotNull(result),
            () -> assertNotNull(result.hotelId()),
            () -> assertEquals(criteria.hotelId(), result.hotelId()),
            () -> assertNotNull(result.checkIn()),
            () -> assertEquals(criteria.checkIn(), result.checkIn()),
            () -> assertNotNull(result.checkOut()),
            () -> assertEquals(criteria.checkOut(), result.checkOut()),
            () -> assertNotNull(result.ages()),
            () -> assertFalse(result.ages().isEmpty()),
            () -> assertEquals(criteria.ages(), result.ages())
        );
    }

    @Test
    void toEventNull(){
        assertNull(mapper.toEvent(null));
    }

    @Test
    void toEventNullAge(){
        SearchCriteria criteria = new SearchCriteria(
            "valid-hotel",
            LocalDate.MIN,
            LocalDate.MAX,
            null
        );

        SearchEvent result = mapper.toEvent(criteria);

        assertNotNull(result);
        assertNull(result.ages());
    }

}
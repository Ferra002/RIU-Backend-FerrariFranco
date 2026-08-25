package com.mindata.riu.writer.infrastructure.in.kafka.mapper;

import com.mindata.riu.writer.domain.dto.SearchCriteria;
import com.mindata.riu.writer.factory.TestClassBuilder;
import com.mindata.riu.writer.infrastructure.in.kafka.dto.SearchEvent;
import com.mindata.riu.writer.infrastructure.in.kafka.dto.SearchKey;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KafkaSearchMapperTest {

    KafkaSearchMapper mapper = Mappers.getMapper(KafkaSearchMapper.class);

    @Test
    void toCriteria() {
        SearchKey key = TestClassBuilder.SEARCH_KEY;
        SearchEvent event = TestClassBuilder.SEARCH_EVENT;

        SearchCriteria result = mapper.toCriteria(key, event);

        assertAll(
            () -> assertNotNull(result),
            () -> assertNotNull(result.searchId()),
            () -> assertEquals(key.searchId(), result.searchId()),
            () -> assertNotNull(result.hotelId()),
            () -> assertEquals(event.hotelId(), result.hotelId()),
            () -> assertNotNull(result.checkIn()),
            () -> assertEquals(event.checkIn(), result.checkIn()),
            () -> assertNotNull(result.checkOut()),
            () -> assertEquals(event.checkOut(), result.checkOut()),
            () -> assertNotNull(result.ages()),
            () -> assertEquals(event.ages(), result.ages())
        );
    }

    @Test
    void toCriteriaNullKey(){
        var result = mapper.toCriteria(null, TestClassBuilder.SEARCH_EVENT);

        assertAll(
            () -> assertNotNull(result),
            () -> assertNull(result.searchId()),
            () -> assertNotNull(result.hotelId()),
            () -> assertNotNull(result.checkIn()),
            () -> assertNotNull(result.checkOut()),
            () -> assertNotNull(result.ages())
        );
    }

    @Test
    void toCriteriaNullEvent(){
        var result = mapper.toCriteria(TestClassBuilder.SEARCH_KEY, null);

        assertAll(
            () -> assertNotNull(result),
            () -> assertNotNull(result.searchId()),
            () -> assertNull(result.hotelId()),
            () -> assertNull(result.checkIn()),
            () -> assertNull(result.checkOut()),
            () -> assertNull(result.ages())
        );
    }

    @Test
    void toCriteriaNullKeyAndEvent(){
        assertNull(mapper.toCriteria(null, null));
    }

    @Test
    void toCriteriaNullAge(){
        SearchKey key = TestClassBuilder.SEARCH_KEY;
        SearchEvent event = new SearchEvent(
            "abcd123",
            LocalDate.MIN,
            LocalDate.MAX,
            null
        );

        SearchCriteria result = mapper.toCriteria(key, event);

        assertAll(
            () -> assertNotNull(result),
            () -> assertNull(result.ages())
        );
    }

}
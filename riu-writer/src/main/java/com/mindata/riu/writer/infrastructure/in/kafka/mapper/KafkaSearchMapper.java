package com.mindata.riu.writer.infrastructure.in.kafka.mapper;

import com.mindata.riu.writer.domain.dto.SearchCriteria;
import com.mindata.riu.writer.infrastructure.in.kafka.dto.SearchEvent;
import com.mindata.riu.writer.infrastructure.in.kafka.dto.SearchKey;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface KafkaSearchMapper {

    SearchCriteria toCriteria(SearchKey key, SearchEvent event);

}

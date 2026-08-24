package com.mindata.riu.infrastructure.out.kafka.mapper;

import com.mindata.riu.domain.model.SearchCriteria;
import com.mindata.riu.infrastructure.out.kafka.dto.SearchEvent;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface KafkaSearchMapper {

    SearchEvent toEvent(SearchCriteria criteria);

}

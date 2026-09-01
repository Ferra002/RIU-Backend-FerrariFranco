package com.mindata.riu.writer.infrastructure.out.persistence.mapper;

import com.mindata.riu.writer.application.port.out.dto.SearchRepositoryDTO;
import com.mindata.riu.writer.infrastructure.out.persistence.entity.SearchEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SearchPersistenceMapper {

    @Mapping(target = "rawAges", expression = "java(toRawAges(criteria.ages()))")
    SearchEntity toEntity(SearchRepositoryDTO criteria);

    SearchRepositoryDTO toDto(SearchEntity entity);

    default String toRawAges(List<Integer> ages){
        if(ages == null) return null;

        return ages.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
    }

}

package com.mindata.riu.writer.infrastructure.out.persistence.mapper;

import com.mindata.riu.writer.application.port.out.dto.SearchRepositoryDTO;
import com.mindata.riu.writer.infrastructure.out.persistence.entity.SearchEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SearchPersistenceMapper {

    SearchEntity toEntity(SearchRepositoryDTO criteria);

    SearchRepositoryDTO toDto(SearchEntity entity);

}

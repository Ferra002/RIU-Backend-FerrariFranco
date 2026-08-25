package com.mindata.riu.searcher.infrastructure.out.persistence.mapper;

import com.mindata.riu.searcher.application.port.out.dto.SearchRepositoryDTO;
import com.mindata.riu.searcher.infrastructure.out.persistence.entity.SearchEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SearchPersistenceMapper {

    SearchRepositoryDTO toDto(SearchEntity entity);

}

package com.mindata.riu.writer.application.mapper;

import com.mindata.riu.writer.application.port.out.dto.SearchRepositoryDTO;
import com.mindata.riu.writer.domain.dto.SearchCriteria;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SearchRepositoryMapper {

    SearchRepositoryDTO toDto(SearchCriteria criteria);

}

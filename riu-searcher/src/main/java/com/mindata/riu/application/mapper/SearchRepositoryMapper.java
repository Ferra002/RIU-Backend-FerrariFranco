package com.mindata.riu.application.mapper;

import com.mindata.riu.application.port.out.dto.SearchRepositoryDTO;
import com.mindata.riu.domain.model.SearchCount;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SearchRepositoryMapper {

    SearchCount toCount(SearchRepositoryDTO dto);

}

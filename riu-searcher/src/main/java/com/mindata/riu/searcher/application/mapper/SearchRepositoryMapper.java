package com.mindata.riu.searcher.application.mapper;

import com.mindata.riu.searcher.application.port.out.dto.SearchRepositoryDTO;
import com.mindata.riu.searcher.domain.model.SearchCriteria;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SearchRepositoryMapper {

    SearchCriteria toSearchCriteria(SearchRepositoryDTO dto);

}

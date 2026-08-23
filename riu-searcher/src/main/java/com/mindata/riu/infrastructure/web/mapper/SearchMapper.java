package com.mindata.riu.infrastructure.web.mapper;

import com.mindata.riu.domain.model.Search;
import com.mindata.riu.domain.model.SearchCriteria;
import com.mindata.riu.infrastructure.web.dto.request.SearchRequestDTO;
import com.mindata.riu.infrastructure.web.dto.response.SearchResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SearchMapper {

    SearchCriteria toCriteria(SearchRequestDTO dto);

    SearchResponseDTO toResponse(Search search);

}

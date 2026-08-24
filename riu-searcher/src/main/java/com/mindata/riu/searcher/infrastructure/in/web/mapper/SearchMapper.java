package com.mindata.riu.searcher.infrastructure.in.web.mapper;

import com.mindata.riu.searcher.domain.model.Search;
import com.mindata.riu.searcher.domain.model.SearchCriteria;
import com.mindata.riu.searcher.infrastructure.in.web.dto.request.SearchRequestDTO;
import com.mindata.riu.searcher.infrastructure.in.web.dto.response.SearchResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SearchMapper {

    SearchCriteria toCriteria(SearchRequestDTO dto);

    SearchResponseDTO toResponse(Search search);

}

package com.mindata.riu.infrastructure.web.mapper;

import com.mindata.riu.domain.model.Count;
import com.mindata.riu.domain.model.SearchCriteria;
import com.mindata.riu.infrastructure.web.dto.request.SearchRequestDTO;
import com.mindata.riu.infrastructure.web.dto.response.SearchResponseDTO;

public interface SearchMapper {

    SearchCriteria toCriteria(SearchRequestDTO dto);

    SearchResponseDTO toResponse(Count searchId);

}

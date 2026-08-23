package com.mindata.riu.infrastructure.web.mapper;

import com.mindata.riu.domain.model.SearchCriteria;
import com.mindata.riu.infrastructure.web.dto.response.CountResponseDTO;

public interface CountMapper {

    CountResponseDTO toResponse(SearchCriteria criteria);

}

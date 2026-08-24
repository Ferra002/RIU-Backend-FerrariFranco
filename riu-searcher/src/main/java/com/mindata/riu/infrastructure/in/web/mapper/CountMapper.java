package com.mindata.riu.infrastructure.in.web.mapper;

import com.mindata.riu.domain.model.SearchCount;
import com.mindata.riu.infrastructure.in.web.dto.response.CountResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CountMapper {

    CountResponseDTO toResponse(SearchCount criteria);

}

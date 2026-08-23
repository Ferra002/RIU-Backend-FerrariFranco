package com.mindata.riu.infrastructure.web.mapper;

import com.mindata.riu.domain.model.Count;
import com.mindata.riu.infrastructure.web.dto.response.CountResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CountMapper {

    CountResponseDTO toResponse(Count criteria);

}

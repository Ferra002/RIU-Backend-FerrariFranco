package com.mindata.riu.application.mapper;

import com.mindata.riu.application.port.out.dto.SearchRepositoryDTO;
import com.mindata.riu.domain.model.Count;

public interface SearchRepositoryMapper {

    Count toCount(SearchRepositoryDTO dto);

}

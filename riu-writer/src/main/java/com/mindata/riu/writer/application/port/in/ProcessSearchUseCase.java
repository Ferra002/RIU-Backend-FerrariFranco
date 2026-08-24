package com.mindata.riu.writer.application.port.in;

import com.mindata.riu.writer.domain.dto.SearchCriteria;

public interface ProcessSearchUseCase {

    void process(SearchCriteria searchCriteria);

}

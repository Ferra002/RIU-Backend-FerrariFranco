package com.mindata.riu.searcher.application.port.in;

import com.mindata.riu.searcher.domain.model.SearchCount;

public interface CountUseCase {

    SearchCount count(String searchId);

}

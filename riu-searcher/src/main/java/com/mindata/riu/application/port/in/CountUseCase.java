package com.mindata.riu.application.port.in;

import com.mindata.riu.domain.model.SearchCount;

public interface CountUseCase {

    SearchCount count(String searchId);

}

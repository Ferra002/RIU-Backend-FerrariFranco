package com.mindata.riu.application.port.in;

import com.mindata.riu.domain.model.Count;

public interface CountUseCase {

    Count count(String searchId);

}

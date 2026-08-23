package com.mindata.riu.application.port.out;

import com.mindata.riu.application.port.out.dto.SearchRepositoryDTO;

public interface SearchRepository {

    SearchRepositoryDTO count(String searchId);

}

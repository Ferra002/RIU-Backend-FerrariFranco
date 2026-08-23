package com.mindata.riu.application.service;

import com.mindata.riu.application.mapper.SearchRepositoryMapper;
import com.mindata.riu.application.port.in.CountUseCase;
import com.mindata.riu.application.port.out.SearchRepository;
import com.mindata.riu.domain.model.Count;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountService implements CountUseCase {

    private final SearchRepository repository;
    private final SearchRepositoryMapper mapper;

    @Override
    public Count count(String searchId) {
        return mapper.toCount(repository.count(searchId));
    }

}

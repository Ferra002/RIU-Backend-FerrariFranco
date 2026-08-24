package com.mindata.riu.searcher.infrastructure.in.web.controller;

import com.mindata.riu.searcher.application.port.in.CountUseCase;
import com.mindata.riu.searcher.application.port.in.SearchUseCase;
import com.mindata.riu.searcher.infrastructure.in.web.dto.request.SearchRequestDTO;
import com.mindata.riu.searcher.infrastructure.in.web.dto.response.CountResponseDTO;
import com.mindata.riu.searcher.infrastructure.in.web.dto.response.SearchResponseDTO;
import com.mindata.riu.searcher.infrastructure.in.web.mapper.CountMapper;
import com.mindata.riu.searcher.infrastructure.in.web.mapper.SearchMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/hotel-search/v1")
public class RestSearch {

    private final CountMapper countMapper;
    private final SearchMapper searchMapper;

    private final CountUseCase countUseCase;
    private final SearchUseCase searchUseCase;

    @GetMapping("/count")
    public ResponseEntity<CountResponseDTO> count(
        @RequestParam
        @NotBlank(message = "El parámetro 'seachId' no puede ser nulo o estar vacío")
        String searchId
    ){
        return ResponseEntity.ok(
                countMapper.toResponse(countUseCase.count(searchId))
        );
    }

    @PostMapping("/search")
    public ResponseEntity<SearchResponseDTO> search(@RequestBody @Valid SearchRequestDTO request){
        return ResponseEntity.ok(
                searchMapper.toResponse(
                        searchUseCase.postSearch(searchMapper.toCriteria(request))
                )
        );
    }

}

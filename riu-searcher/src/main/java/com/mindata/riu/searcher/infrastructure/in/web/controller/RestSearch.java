package com.mindata.riu.searcher.infrastructure.in.web.controller;

import com.mindata.riu.searcher.application.port.in.CountUseCase;
import com.mindata.riu.searcher.application.port.in.SearchUseCase;
import com.mindata.riu.searcher.infrastructure.in.web.dto.request.SearchRequestDTO;
import com.mindata.riu.searcher.infrastructure.in.web.dto.response.CountResponseDTO;
import com.mindata.riu.searcher.infrastructure.in.web.dto.response.SearchResponseDTO;
import com.mindata.riu.searcher.infrastructure.in.web.mapper.CountMapper;
import com.mindata.riu.searcher.infrastructure.in.web.mapper.SearchMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequiredArgsConstructor
@Tag(
    name = "Search",
    description = "Operaciones relacionadas con búsquedas"
)
public class RestSearch {

    private final CountMapper countMapper;
    private final SearchMapper searchMapper;

    private final CountUseCase countUseCase;
    private final SearchUseCase searchUseCase;

    @Operation(
        summary = "Consultar búsquedas",
        description = "Obtiene una búsqueda específica en base a un identificador, así como la cuenta de búsquedas iguales"
    )
    @ApiResponse(responseCode = "200", description = "Consulta exitosa")
    @ApiResponse(responseCode = "400", description = "Parámetro inválido")
    @ApiResponse(responseCode = "404", description = "Búsqueda no encontrada")
    @GetMapping("/count")
    public ResponseEntity<CountResponseDTO> count(
        @RequestParam
        @NotBlank(message = "El parámetro 'searchId' no puede ser nulo o estar vacío")
        String searchId
    ){
        return ResponseEntity.ok(
                countMapper.toResponse(countUseCase.count(searchId))
        );
    }

    @Operation(
        summary = "Publicar búsqueda",
        description = "Asigna un identificador a una búsqueda, publica la búsqueda y devuelve el identificador generado"
    )
    @ApiResponse(responseCode = "201", description = "Publicación exitosa")
    @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    @PostMapping("/search")
    public ResponseEntity<SearchResponseDTO> search(@RequestBody @Valid SearchRequestDTO request){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                searchMapper.toResponse(
                        searchUseCase.postSearch(searchMapper.toCriteria(request))
                )
        );
    }

}

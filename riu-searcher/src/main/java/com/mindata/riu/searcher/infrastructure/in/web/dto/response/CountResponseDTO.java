package com.mindata.riu.searcher.infrastructure.in.web.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record CountResponseDTO(

    @Schema(
        description = "Generated identifier",
        example = "4f329977-8339-4d6f-9988-348f98ef4a1d"
    )
    String searchId,

    @Schema(
        description = "Resultado de la búsqueda"
    )
    CountSearchDTO search,

    @Schema(
        description = "Cantidad de búsquedas idénticas",
        example = "5"
    )
    Integer count

) {}

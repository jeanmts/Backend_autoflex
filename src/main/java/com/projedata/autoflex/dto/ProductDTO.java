package com.projedata.autoflex.dto;

import lombok.Builder;


import java.util.UUID;

@Builder
public record ProductDTO(
        UUID id,
        String code,
        String name,
        Double value,
        Integer rawMaterialQuantity

) {
}

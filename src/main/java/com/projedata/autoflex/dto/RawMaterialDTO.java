package com.projedata.autoflex.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record RawMaterialDTO(
        UUID id,
        String code,
        String name,
        Integer stockQuantity
) {
}

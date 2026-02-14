package com.projedata.autoflex.dto;

public record RawMaterialRequestDTO(
         String code,
         String name,
         Integer stockQuantity
) {
}

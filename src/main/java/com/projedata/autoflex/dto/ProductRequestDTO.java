package com.projedata.autoflex.dto;



public record ProductRequestDTO(
         String code,
         String name,
         Double value,
         Integer rawMaterialQuantity
) {
}

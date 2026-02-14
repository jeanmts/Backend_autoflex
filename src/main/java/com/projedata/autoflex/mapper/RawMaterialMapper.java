package com.projedata.autoflex.mapper;

import com.projedata.autoflex.dto.RawMaterialDTO;
import com.projedata.autoflex.entity.RawMaterial;
import org.springframework.stereotype.Component;

@Component
public class RawMaterialMapper {

    public RawMaterialDTO toDto(RawMaterial rawMaterial) {
        return RawMaterialDTO.builder()
                .id(rawMaterial.getId())
                .name(rawMaterial.getName())
                .code(rawMaterial.getCode())
                .stockQuantity(rawMaterial.getStockQuantity())
                .build();
    }
}

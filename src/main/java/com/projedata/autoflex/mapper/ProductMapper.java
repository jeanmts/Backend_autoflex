package com.projedata.autoflex.mapper;

import com.projedata.autoflex.dto.ProductDTO;
import com.projedata.autoflex.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductDTO toDto(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .code(product.getCode())
                .value(product.getValue())
                .rawMaterialQuantity(product.getRawMaterialQuantity())
                .build();
    }
}

package com.projedata.autoflex.mapper;

import com.projedata.autoflex.dto.SuggestedItemDTO;
import com.projedata.autoflex.entity.Product;
import com.projedata.autoflex.entity.ProductRawMaterial;
import org.springframework.stereotype.Component;

@Component
public class ProductionMapper {

    public SuggestedItemDTO toDto(Product product) {
        return SuggestedItemDTO.builder()
                .product_name(product.getName())
                .code(product.getCode())
                .unit_price(product.getValue())
                .build();
    }

}

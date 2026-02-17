package com.projedata.autoflex.service;


import com.projedata.autoflex.dto.SuggestedItemDTO;
import com.projedata.autoflex.entity.Product;
import com.projedata.autoflex.entity.ProductRawMaterial;
import com.projedata.autoflex.entity.RawMaterial;
import com.projedata.autoflex.repository.ProductRawMaterialRepository;
import com.projedata.autoflex.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class ProductionService {

    private final ProductRepository productRepository;

    private final ProductRawMaterialRepository productRawMaterialRepository;

    @Transactional(readOnly = true)
    public List<SuggestedItemDTO> listProduction() {

        List<Product> products = productRepository.findAllByOrderByValueDesc();

        List<SuggestedItemDTO> suggestedItemDTOS = new ArrayList<>();

        for (Product product : products) {

            List<ProductRawMaterial> productRawMaterials = productRawMaterialRepository
                    .findByProductId(product.getId());

            int maxQuantityToProduce = Integer.MAX_VALUE;

            for (ProductRawMaterial prm : productRawMaterials) {
                RawMaterial rawMaterial = prm.getId().getRawMaterial();
                int availableQuantity = rawMaterial.getStockQuantity();
                int requiredPerUnit = prm.getRequiredQuantity();


                if (requiredPerUnit <= 0) {
                    maxQuantityToProduce = 0;
                    break;
                }

                int possibleQuantity = availableQuantity / requiredPerUnit;
                maxQuantityToProduce = Math.min(maxQuantityToProduce, possibleQuantity);
            }

            if (maxQuantityToProduce > 0 && maxQuantityToProduce != Integer.MAX_VALUE) {
                double totalValue = product.getValue() * maxQuantityToProduce;

                DecimalFormat df = new DecimalFormat("0.00");

                SuggestedItemDTO dto = SuggestedItemDTO.builder()
                        .code(product.getCode())
                        .product_name(product.getName())
                        .quantity(maxQuantityToProduce)
                        .unit_price(product.getValue())
                        .total_value(df.format(totalValue))
                        .build();

                suggestedItemDTOS.add(dto);
            }
        }

        return suggestedItemDTOS;
    }

}

package com.projedata.autoflex.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "DTO com sugestão de produção de um produto")
public record SuggestedItemDTO(
        @Schema(description = "Código do produto", example = "PROD001")
        String code,

        @Schema(description = "Nome do produto", example = "Produto A")
        String product_name,

        @Schema(description = "Quantidade máxima que pode ser produzida com o estoque disponível", example = "5")
        Integer quantity,

        @Schema(description = "Preço unitário do produto", example = "100.0")
        Double unit_price,

        @Schema(description = "Valor total da produção sugerida (quantidade * preço unitário)", example = "500.0")
        String total_value
) {
}

package com.projedata.autoflex.controller;

import com.projedata.autoflex.dto.SuggestedItemDTO;
import com.projedata.autoflex.service.ProductionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/production")
@RequiredArgsConstructor
@Tag(name = "Endpoints de Sugestão de Produção", description = "Gerencia sugestões de produção baseado nas matérias-primas em estoque")
public class ProductionController {

    private final ProductionService productionService;

    @GetMapping
    public ResponseEntity<List<SuggestedItemDTO>> listProduction() {
        List<SuggestedItemDTO> suggestions = productionService.listProduction();
        return ResponseEntity.ok().body(suggestions);
    }

}





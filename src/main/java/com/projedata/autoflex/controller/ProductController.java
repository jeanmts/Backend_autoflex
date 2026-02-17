package com.projedata.autoflex.controller;

import com.projedata.autoflex.dto.ProductDTO;
import com.projedata.autoflex.dto.ProductRequestDTO;
import com.projedata.autoflex.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/products")
@RequiredArgsConstructor
@Tag(name = "Endpoints de Gerenciamento de produtos",  description ="Gerencia os produtos, incluindo criação, atualização, consulta e exclusão. Permite associar produtos às matérias-primas necessárias para sua produção.")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> listAllProducts() {
        return ResponseEntity.ok().body(productService.listAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findProducts(@PathVariable UUID id) {
        return ResponseEntity.ok().body(productService.findProduct(id));
    }

    @PostMapping("/{idRawMaterial}")
    public ResponseEntity<ProductDTO> registerProduct(@Valid @RequestBody ProductRequestDTO productRequestDTO, @PathVariable UUID idRawMaterial) {
        return ResponseEntity.ok().body(productService.registerProduct(productRequestDTO, idRawMaterial));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@Valid @RequestBody ProductRequestDTO productRequestDTO, @PathVariable UUID id){
        return ResponseEntity.ok().body(productService.updateProduct(productRequestDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}

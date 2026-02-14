package com.projedata.autoflex.controller;


import com.projedata.autoflex.dto.RawMaterialDTO;
import com.projedata.autoflex.dto.RawMaterialRequestDTO;
import com.projedata.autoflex.service.RawMaterialService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/material")
@RequiredArgsConstructor
@Tag(name = "Endpoints de Gerenciamento das matérias-primas", description = "Gerencia as matérias-primas, incluindo criação, atualização, consulta e exclusão. Permite atualizar a quantidade em estoque das matérias-primas.")
public class RawMaterialController {

    private final RawMaterialService rawMaterialService;

    @GetMapping
    public ResponseEntity<List<RawMaterialDTO>> listAllRawMaterial() {
        return ResponseEntity.ok().body(rawMaterialService.listAllRawMaterial());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RawMaterialDTO> findRawMaterial(@PathVariable UUID id) {
        return ResponseEntity.ok().body(rawMaterialService.findRawMaterial(id));
    }

    @PostMapping
    public ResponseEntity<RawMaterialDTO> registerRawMaterial(@Valid @RequestBody RawMaterialRequestDTO rawMaterialRequestDTO) {
        return ResponseEntity.ok().body(rawMaterialService.registerRawMaterial(rawMaterialRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RawMaterialDTO> updateRawMaterial(@Valid @RequestBody RawMaterialRequestDTO rawMaterialRequestDTO, @PathVariable UUID id){
        return ResponseEntity.ok().body(rawMaterialService.updateRawMaterial(rawMaterialRequestDTO, id));
    }

    @PutMapping("/quantity/{id}")
    public ResponseEntity<RawMaterialDTO> updateQuantityRawMaterial(@Valid @RequestBody Integer quantity, @PathVariable UUID id){
        return ResponseEntity.ok().body(rawMaterialService.updateQuantityRawMaterial(quantity, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRawMaterial(@PathVariable UUID id) {
        rawMaterialService.deleteRawMaterial(id);
        return ResponseEntity.noContent().build();
    }
}

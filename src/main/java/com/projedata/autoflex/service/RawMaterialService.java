package com.projedata.autoflex.service;

import com.projedata.autoflex.dto.RawMaterialDTO;
import com.projedata.autoflex.dto.RawMaterialRequestDTO;
import com.projedata.autoflex.entity.RawMaterial;
import com.projedata.autoflex.exception.ProductNotFoundException;
import com.projedata.autoflex.exception.RawMaterialDuplicateException;
import com.projedata.autoflex.exception.RawMaterialInsufficientException;
import com.projedata.autoflex.exception.RawMaterialNotFoundException;
import com.projedata.autoflex.mapper.RawMaterialMapper;
import com.projedata.autoflex.repository.RawMaterialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RawMaterialService {

    private final RawMaterialRepository rawMaterialRepository;

    private final RawMaterialMapper rawMaterialMapper;

    public List<RawMaterialDTO> listAllRawMaterial() {
        return rawMaterialRepository.findAll().stream().map(rawMaterialMapper::toDto).toList();
    }

    public RawMaterialDTO findRawMaterial(UUID id) {
        return rawMaterialRepository.findById(id).map(rawMaterialMapper::toDto)
                .orElseThrow(()-> new ProductNotFoundException("A matéria-prima informado não foi encontrado!"));
    }

    @Transactional
    public RawMaterialDTO registerRawMaterial(RawMaterialRequestDTO rawMaterialRequestDTO) {
        Optional<RawMaterial> rawMaterial = rawMaterialRepository.findByCode(rawMaterialRequestDTO.code());

        if (rawMaterial.isPresent()){
            throw new RawMaterialDuplicateException("A matéria-prima informada já esta cadastrada!");
        }

        RawMaterial material = RawMaterial.builder()
                .name(rawMaterialRequestDTO.name())
                .code(rawMaterialRequestDTO.code())
                .stockQuantity(rawMaterialRequestDTO.stockQuantity())
                .build();

        return rawMaterialMapper.toDto(rawMaterialRepository.save(material));

    }

    @Transactional
    public RawMaterialDTO updateRawMaterial(RawMaterialRequestDTO rawMaterialRequestDTO, UUID id) {

        RawMaterial rawMaterial = rawMaterialRepository.findById(id)
                .orElseThrow(()-> new RawMaterialNotFoundException("A matéria-prima informada não foi encontrada!"));

        RawMaterial material = RawMaterial.builder()
                .id(rawMaterial.getId())
                .name(rawMaterialRequestDTO.name())
                .code(rawMaterialRequestDTO.code())
                .stockQuantity(rawMaterialRequestDTO.stockQuantity())
                .build();

        return rawMaterialMapper.toDto(rawMaterialRepository.save(material));
    }

    @Transactional
    public RawMaterialDTO updateQuantityRawMaterial(Integer quantity, UUID id) {

        RawMaterial rawMaterial = rawMaterialRepository.findById(id)
                .orElseThrow(()-> new RawMaterialNotFoundException("A matéria-prima informada não foi encontrada!"));

        if (rawMaterial.getStockQuantity() < quantity) {
            throw new RawMaterialInsufficientException
                    ("A quantidade de matéria-prima para esse produto é insuficiente,quantidade de matéria-prima disponível é: " + rawMaterial.getStockQuantity());
        }

        RawMaterial material = RawMaterial.builder()
                .id(rawMaterial.getId())
                .name(rawMaterial.getName())
                .code(rawMaterial.getCode())
                .stockQuantity(rawMaterial.getStockQuantity() + quantity)
                .build();

        return rawMaterialMapper.toDto(rawMaterialRepository.save(material));
    }

    @Transactional
    public RawMaterialDTO toDecreaseQuantityRawMaterial(Integer quantity, UUID id) {

        RawMaterial rawMaterial = rawMaterialRepository.findById(id)
                .orElseThrow(()-> new RawMaterialNotFoundException("A matéria-prima informada não foi encontrada!"));

        if (rawMaterial.getStockQuantity() < quantity) {
            throw new RawMaterialInsufficientException
                    ("A quantidade de matéria-prima para esse produto é insuficiente, quantidade de matéria-prima disponível é: " + rawMaterial.getStockQuantity());
        }

        RawMaterial material = RawMaterial.builder()
                .id(rawMaterial.getId())
                .name(rawMaterial.getName())
                .code(rawMaterial.getCode())
                .stockQuantity(rawMaterial.getStockQuantity() - quantity)
                .build();

        return rawMaterialMapper.toDto(rawMaterialRepository.save(material));
    }

    @Transactional
    public  void deleteRawMaterial(UUID id) {
        RawMaterial rawMaterial = rawMaterialRepository.findById(id)
                .orElseThrow(()-> new RawMaterialNotFoundException("A matéria-prima informada não foi encontrada!"));

        rawMaterialRepository.deleteById(id);
    }

}

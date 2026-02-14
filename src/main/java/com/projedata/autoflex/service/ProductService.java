package com.projedata.autoflex.service;

import com.projedata.autoflex.dto.ProductDTO;
import com.projedata.autoflex.dto.ProductRequestDTO;
import com.projedata.autoflex.entity.Product;
import com.projedata.autoflex.entity.ProductRawMaterial;
import com.projedata.autoflex.entity.RawMaterial;
import com.projedata.autoflex.exception.ProductNotFoundException;
import com.projedata.autoflex.exception.RawMaterialNotFoundException;
import com.projedata.autoflex.mapper.ProductMapper;
import com.projedata.autoflex.repository.ProductRawMaterialRepository;
import com.projedata.autoflex.repository.ProductRepository;
import com.projedata.autoflex.repository.RawMaterialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    private  final RawMaterialRepository rawMaterialRepository;

    private final RawMaterialService rawMaterialService;

    private final ProductMapper productMapper;

    private final ProductRawMaterialRepository productRawMaterialRepository;

    public List<ProductDTO> listAllProducts () {
        return productRepository.findAll().stream().map(productMapper::toDto).toList();
    }

    public ProductDTO findProduct(UUID id) {
        return productRepository.findById(id).map(productMapper::toDto)
                .orElseThrow(()-> new ProductNotFoundException("O produto informado não foi encontrado!"));
    }

    @Transactional
    public ProductDTO registerProduct(ProductRequestDTO productRequestDTO, UUID idRawMaterial) {

        RawMaterial rawMaterial = rawMaterialRepository.findById(idRawMaterial)
                .orElseThrow(()-> new RawMaterialNotFoundException("A matéria-prima informada não existe!"));

        Product product = Product.builder()
                .name(productRequestDTO.name())
                .code(productRequestDTO.code())
                .value(productRequestDTO.value())
                .build();

        // Salvar o produto primeiro
        Product savedProduct = productRepository.save(product);
        System.out.println("=== PRODUTO SALVO ===");
        System.out.println("ID Produto: " + savedProduct.getId());
        System.out.println("Nome: " + savedProduct.getName());

        // Criar e salvar o relacionamento entre produto e matéria-prima
        ProductRawMaterial productRawMaterial = new ProductRawMaterial(
                savedProduct,
                rawMaterial,
                productRequestDTO.rawMaterialQuantity()
        );

        System.out.println("=== RELACIONAMENTO A SALVAR ===");
        System.out.println("ID Produto: " + productRawMaterial.getId().getProduct().getId());
        System.out.println("ID Matéria-Prima: " + productRawMaterial.getId().getRawMaterial().getId());
        System.out.println("Quantidade Requerida: " + productRawMaterial.getRequiredQuantity());

        ProductRawMaterial savedRelationship = productRawMaterialRepository.save(productRawMaterial);
        System.out.println("=== RELACIONAMENTO SALVO COM SUCESSO ===");

        // Diminuir a quantidade da matéria-prima em estoque
        rawMaterialService.toDecreaseQuantityRawMaterial(productRequestDTO.rawMaterialQuantity(), idRawMaterial);

        return productMapper.toDto(savedProduct);
    }

    @Transactional
    public ProductDTO updateProduct (ProductRequestDTO productRequestDTO, UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("O produto informado não foi encontrado!"));

        Product newProduct = Product.builder()
                .id(product.getId())
                .name(productRequestDTO.name())
                .code(productRequestDTO.code())
                .value(productRequestDTO.value())
                .rawMaterialQuantity(productRequestDTO.rawMaterialQuantity())
                .build();

        return productMapper.toDto(productRepository.save(newProduct));
    }

    @Transactional
    public void deleteProduct(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("O produto informado não foi encontrado!"));
        productRepository.deleteById(id);
    }
}

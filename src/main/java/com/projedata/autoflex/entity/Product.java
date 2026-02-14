package com.projedata.autoflex.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


import java.util.UUID;

@NoArgsConstructor @AllArgsConstructor
@Builder
@Getter @Setter
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "O código do produto deve ser informado!")
    @Column(length = 20)
    private String code;

    @NotBlank(message = "O nome do produto deve ser informado!")
    @Size(min = 5, message = "O nome do produto deve conter no mínimo 5 caracteres!")
    private String name;

    @NotNull(message = "O valor do produto deve ser informado!")
    private Double value;

    @Transient
    Integer rawMaterialQuantity;

}

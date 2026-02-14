package com.projedata.autoflex.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "raw_material")
public class RawMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "O código do matéria-prima deve ser informado!")
    @Column(length = 20)
    private String code;

    @NotBlank(message = "O nome da matéria-prima deve ser informado!")
    @Size(min = 5, message = "O nome da matéria prima deve conter no mínimo 3 caracteres!")
    private String name;

    @NotNull(message = "O estoque da matéria-prima deve ser informado!")
    @Min(value = 1, message = "O valor mínimo para o estoque é 1!")
    private Integer stockQuantity;

}

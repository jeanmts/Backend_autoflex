package com.projedata.autoflex.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
@Table(name = "product_raw_material")
public class ProductRawMaterial {

    @EmbeddedId
    private ProductRawMaterialPK id;

    @Column(name = "required_quantity")
    private Integer requiredQuantity;

    public ProductRawMaterial(Product product, RawMaterial rawMaterial, Integer requiredQuantity) {
        this.id = new ProductRawMaterialPK();
        this.id.setProduct(product);
        this.id.setRawMaterial(rawMaterial);
        this.requiredQuantity = requiredQuantity;
    }

}

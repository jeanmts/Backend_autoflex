package com.projedata.autoflex.repository;


import com.projedata.autoflex.entity.Product;
import com.projedata.autoflex.entity.ProductRawMaterial;
import com.projedata.autoflex.entity.ProductRawMaterialPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRawMaterialRepository extends JpaRepository<ProductRawMaterial, ProductRawMaterialPK> {

    @Query(value = "SELECT * FROM product_raw_material WHERE id_product = :productId", nativeQuery = true)
    List<ProductRawMaterial> findByProductId(@Param("productId") UUID productId);

    @Query("SELECT prm FROM ProductRawMaterial prm WHERE prm.id.product = :product")
    List<ProductRawMaterial> findByProduct(@Param("product") Product product);
}

package com.projedata.autoflex.repository;

import com.projedata.autoflex.entity.RawMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RawMaterialRepository extends JpaRepository<RawMaterial, UUID> {
    Optional<RawMaterial> findByCode(String code);
}

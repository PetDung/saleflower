package com.code.orishop.repository;

import com.code.orishop.model.entity.BrandEntity;
import com.code.orishop.model.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<ImageEntity,Long> {
    List<ImageEntity> findByProduct_Id(Long id);
}

package com.andresvg8.fixedassetsapi.repository;

import com.andresvg8.fixedassetsapi.entity.FixedAsset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface FixedAssetRepository extends JpaRepository<FixedAsset, Long> {
    Optional<List<FixedAsset>> findByType(String type);

    Optional<List<FixedAsset>> findByPurchaseDate(LocalDate purchaseDate);

    Optional<List<FixedAsset>> findBySerial(String serial);

}

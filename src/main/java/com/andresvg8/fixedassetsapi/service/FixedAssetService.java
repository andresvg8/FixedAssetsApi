package com.andresvg8.fixedassetsapi.service;

import com.andresvg8.fixedassetsapi.entity.FixedAsset;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FixedAssetService {
    Optional<List<FixedAsset>> findByType(String type);

    FixedAsset save(FixedAsset asset);

    Optional<List<FixedAsset>> findByPurchaseDate(LocalDate purchaseDate);

    Optional<List<FixedAsset>> findBySerial(String serial);

    Optional<FixedAsset> findById(Long id);

    Optional<List<FixedAsset>> findAll();
}

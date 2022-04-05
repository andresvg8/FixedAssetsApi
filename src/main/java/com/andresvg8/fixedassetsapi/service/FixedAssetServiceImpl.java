package com.andresvg8.fixedassetsapi.service;

import com.andresvg8.fixedassetsapi.entity.FixedAsset;
import com.andresvg8.fixedassetsapi.repository.FixedAssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FixedAssetServiceImpl implements FixedAssetService {
    @Autowired
    private FixedAssetRepository fixedAssetRepository;

    @Override
    public Optional<List<FixedAsset>> findByType(String type) {
        return fixedAssetRepository.findByType(type);
    }

    @Override
    public FixedAsset save(FixedAsset asset) {
        return fixedAssetRepository.save(asset);
    }

    @Override
    public Optional<List<FixedAsset>> findByPurchaseDate(LocalDate purchaseDate) {
        return fixedAssetRepository.findByPurchaseDate(purchaseDate);
    }

    @Override
    public Optional<List<FixedAsset>> findBySerial(String serial) {
        return fixedAssetRepository.findBySerial(serial);
    }

    @Override
    public Optional<FixedAsset> findById(Long id) {
        return fixedAssetRepository.findById(id);
    }
}

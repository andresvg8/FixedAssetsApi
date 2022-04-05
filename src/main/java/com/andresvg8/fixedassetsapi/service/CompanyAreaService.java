package com.andresvg8.fixedassetsapi.service;

import com.andresvg8.fixedassetsapi.entity.CompanyArea;

import java.util.List;
import java.util.Optional;

public interface CompanyAreaService {
    public Optional<CompanyArea> findById(Long companyareaId);

    public CompanyArea save(CompanyArea companyArea);

    List<CompanyArea> findAll();
}

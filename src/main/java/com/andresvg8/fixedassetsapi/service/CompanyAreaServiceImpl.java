package com.andresvg8.fixedassetsapi.service;

import com.andresvg8.fixedassetsapi.entity.CompanyArea;
import com.andresvg8.fixedassetsapi.repository.CompanyAreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyAreaServiceImpl implements CompanyAreaService {
    @Autowired
    private CompanyAreaRepository companyAreaRepository;

    @Override
    public Optional<CompanyArea> findById(Long companyareaId) {
        return companyAreaRepository.findById(companyareaId);
    }

    @Override
    public CompanyArea save(CompanyArea companyArea) {
        return companyAreaRepository.save(companyArea);
    }

    @Override
    public List<CompanyArea> findAll() {
        return companyAreaRepository.findAll();
    }
}

package com.andresvg8.fixedassetsapi.repository;

import com.andresvg8.fixedassetsapi.entity.CompanyArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyAreaRepository extends JpaRepository<CompanyArea, Long> {
}

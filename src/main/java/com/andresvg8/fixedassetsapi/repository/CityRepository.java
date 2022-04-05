package com.andresvg8.fixedassetsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andresvg8.fixedassetsapi.entity.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}

package com.andresvg8.fixedassetsapi.service;

import java.util.List;
import java.util.Optional;

import com.andresvg8.fixedassetsapi.entity.City;
import com.andresvg8.fixedassetsapi.repository.CityRepository;

public interface CityService {
	public Optional<City> findById(Long cityId);
	
	public City save(City city);

    List<City> findAll();
}

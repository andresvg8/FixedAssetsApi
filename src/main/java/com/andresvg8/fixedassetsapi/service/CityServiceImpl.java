package com.andresvg8.fixedassetsapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andresvg8.fixedassetsapi.entity.City;
import com.andresvg8.fixedassetsapi.repository.CityRepository;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityRepository cityRepository;

	@Override
	public Optional<City> findById(Long cityId) {
		return cityRepository.findById(cityId);
	}

	@Override
	public City save(City city) {
		return cityRepository.save(city);
	}

	@Override
	public List<City> findAll() {
		return cityRepository.findAll();
	}
}

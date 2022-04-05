package com.andresvg8.fixedassetsapi.service;

import com.andresvg8.fixedassetsapi.entity.Employee;
import com.andresvg8.fixedassetsapi.repository.EmployeeRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

	@Override
	public Optional<Employee> findById(Long employeeId) {
		return employeeRepository.findById(employeeId);
	}
}

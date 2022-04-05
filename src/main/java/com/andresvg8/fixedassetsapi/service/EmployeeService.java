package com.andresvg8.fixedassetsapi.service;

import java.util.Optional;

import com.andresvg8.fixedassetsapi.entity.Employee;

public interface EmployeeService {
	public Optional<Employee> findById(Long employeeId);
	
    public Employee save(Employee employee);
}

/**
 * 
 */
package com.andresvg8.fixedassetsapi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.andresvg8.fixedassetsapi.entity.Employee;
import com.andresvg8.fixedassetsapi.entity.FixedAsset;
import com.andresvg8.fixedassetsapi.repository.FixedAssetRepository;

/**
 * @author ANDRES-1
 *
 */
//@RunWith(MockitoJUnitRunner.class)
class FixedAssetServiceImplTest {
	
	@Mock
    private FixedAssetRepository fixedAssetRepository;

    @InjectMocks
    private FixedAssetService fixedAssetService;
    @InjectMocks
    private EmployeeService employeeService;
	
	@Test
	void test() {
		FixedAsset testAsset = new FixedAsset();
		testAsset.setName("MacBook Pro");
		testAsset.setDescription("This is a test description.");
		testAsset.setType("Computer");
		testAsset.setSerial("C45");
		testAsset.setInventoryNumber("A12B34");
		testAsset.setWeight(1.5);
		testAsset.setHeight(0.02);
		testAsset.setWidth(0.3);
		testAsset.setLongitude(0.23);
		testAsset.setPurchaseDate(LocalDate.of(2022, 01, 14));
		testAsset.setPurchasePrice(7654321D);
		Employee testEmployee = new Employee();
		testEmployee.setName("Akdfjdk Vdfkvjbdvk Glhekvb");
		testEmployee = employeeService.save(testEmployee);
		testAsset.setEmployee(testEmployee);
		
		when(fixedAssetService.save(ArgumentMatchers.any(FixedAsset.class))).thenReturn(testAsset);
		
		FixedAsset createdFixedAsset = fixedAssetService.save(testAsset);
		
		assertThat(createdFixedAsset.getId()!=null);
		verify(fixedAssetRepository).save(testAsset);
	}

}

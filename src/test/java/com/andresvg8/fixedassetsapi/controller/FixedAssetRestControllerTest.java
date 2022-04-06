/**
 * 
 */
package com.andresvg8.fixedassetsapi.controller;

import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.http.RequestEntity.post;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.andresvg8.fixedassetsapi.entity.Employee;
import com.andresvg8.fixedassetsapi.entity.FixedAsset;
import com.andresvg8.fixedassetsapi.service.EmployeeService;
import com.andresvg8.fixedassetsapi.service.FixedAssetService;

/**
 * @author ANDRES-1
 *
 */
@WebMvcTest(FixedAssetRestController.class)
class FixedAssetRestControllerTest {
	@MockBean
	private FixedAssetService fixedAssetService;
	@MockBean
	private EmployeeService employeeService;
	@LocalServerPort
	int port;

	@Autowired
	private MockMvc mockMvc;

	/**
	 * Test method for {@link com.andresvg8.fixedassetsapi.controller.FixedAssetRestController#createFixedAsset(com.andresvg8.fixedassetsapi.entity.FixedAsset)}.
	 */
	@Test
	void testCreateFixedAsset() {
		try{
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
			//testAsset = fixedAssetService.save(testAsset);
			/*mockMvc.perform(post("/create")
							.contentType(MediaType.APPLICATION_JSON)
							.content(JsonUtil.toJson(testAsset))

			).andExpect(HttpStatus.OK);*/
		}
		catch (Exception saveTestException){
			fail("Error saving asset into database");
		}
	}

}

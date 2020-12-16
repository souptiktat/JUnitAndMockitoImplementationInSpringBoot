package com.infytel.InfytelUnitTesting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infytel.InfytelUnitTesting.dto.CustomerDTO;
import com.infytel.InfytelUnitTesting.dto.PlanDTO;
import com.infytel.InfytelUnitTesting.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	List<CustomerDTO> customers = null;
	List<Long> friendFamily = null;
	PlanDTO plan = null;
	@Autowired
	private CustomerService customerService;
	// fetch customer details
	@GetMapping(produces = "application/json")
	public List<CustomerDTO> fetchCustomer() {
		return customerService.fetchCustomer();
	}
	// add customer
	@PostMapping(consumes = "application/json")
	public ResponseEntity<String> createCustomer(@RequestBody CustomerDTO customerDTO) {
		String response = "";
		response = customerService.createCustomer(customerDTO);
		return ResponseEntity.ok(response);
	}
	// update an existing customer
	@PutMapping(value = "/{phoneNumber}", consumes = "application/json")
	public String updateCustomer(@PathVariable("phoneNumber") long phoneNumber, @RequestBody CustomerDTO customerDTO) {
		return customerService.updateCustomer(phoneNumber, customerDTO);
	}
	// delete customer
	@DeleteMapping("/{phoneNumber}")
	public String deleteCustomer(@PathVariable("phoneNumber") long phoneNumber) {
		return customerService.deleteCustomer(phoneNumber);
	}
}

package com.infytel.InfytelUnitTesting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infytel.InfytelUnitTesting.dto.CustomerDTO;
import com.infytel.InfytelUnitTesting.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	// Contacts repository layer to add customer
	public String createCustomer(CustomerDTO customerDTO) {
		return customerRepository.createCustomer(customerDTO);
	}
	// Contacts repository layer to fetch customer
	public List<CustomerDTO> fetchCustomer() {
		return customerRepository.fetchCustomer();
	}
	// Contacts repository layer to delete customer
	public String deleteCustomer(long phoneNumber) {
		return customerRepository.deleteCustomer(phoneNumber);
	}
	// Contacts repository layer to update customer
	public String updateCustomer(long phoneNumber, CustomerDTO customerDTO) {
		return customerRepository.updateCustomer(phoneNumber, customerDTO);
	}
}

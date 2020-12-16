package com.infytel.InfytelUnitTesting.repository;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.infytel.InfytelUnitTesting.dto.CustomerDTO;
import com.infytel.InfytelUnitTesting.dto.FriendFamilyDTO;
import com.infytel.InfytelUnitTesting.dto.PlanDTO;

@Repository
public class CustomerRepository {

	List<CustomerDTO> customers = null;
	// populates customer in hard-coded way
	@PostConstruct
	public void initializer() {
		CustomerDTO customerDTO = new CustomerDTO();
		PlanDTO planDTO = new PlanDTO();
		planDTO.setPlanId(1);
		planDTO.setPlanName("Simple");
		planDTO.setLocalRate(3);
		planDTO.setNationalRate(5);
		customerDTO.setAddress("Chennai");
		customerDTO.setAge(18);
		customerDTO.setCurrentPlan(planDTO);
		customerDTO.setGender('m');
		customerDTO.setName("Jack");
		customerDTO.setEmail("Jack@infy.com");
		customerDTO.setPassword("ABC@123");
		customerDTO.setPhoneNo(9951212222l);
		List<FriendFamilyDTO> friendAndFamily = new ArrayList<>();
		friendAndFamily.add(new FriendFamilyDTO(customerDTO.getPhoneNo(), 800000145));
		friendAndFamily.add(new FriendFamilyDTO(customerDTO.getPhoneNo(), 700000145));
		customerDTO.setFriendAndFamily(friendAndFamily);
		customers = new ArrayList<>();
		customers.add(customerDTO);
	}
	// creates customer
	public String createCustomer(CustomerDTO customerDTO) {
		customers.add(customerDTO);
		return "Customer with" + customerDTO.getPhoneNo() + "added successfully";
	}
	// fetches customer
	public List<CustomerDTO> fetchCustomer() {
		return customers;
	}
	// deletes customer
	public String deleteCustomer(long phoneNumber) {
		String response = "Customer of:" + phoneNumber + "\t does not exist";
		for (CustomerDTO customer : customers) {
			if (customer.getPhoneNo() == phoneNumber) {
				customers.remove(customer);
				response = customer.getName() + "of phoneNumber" + customer.getPhoneNo()
						+ "\t got deleted successfully";
				break;
			}
		}
		return response;
	}
	// updates customer
	public String updateCustomer(long phoneNumber, CustomerDTO customerDTO) {
		String response = "Customer of:" + phoneNumber + "\t does not exist";
		for (CustomerDTO customer : customers) {
			if (customer.getPhoneNo() == phoneNumber) {
				if (customerDTO.getName() != null)
					customer.setName(customerDTO.getName());
				if (customerDTO.getAddress() != null)
					customer.setAddress(customerDTO.getAddress());
				if (customerDTO.getPassword() != null)
					customer.setPassword(customerDTO.getPassword());
				customers.set(customers.indexOf(customer), customer);
				response = "Customer of phoneNumber" + customer.getPhoneNo() + "\t got updated successfully";
				break;
			}
		}
		return response;
	}
}

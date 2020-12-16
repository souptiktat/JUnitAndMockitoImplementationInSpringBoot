package com.infytel.InfytelUnitTesting.controller.test;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infytel.InfytelUnitTesting.controller.CustomerController;
import com.infytel.InfytelUnitTesting.dto.CustomerDTO;
import com.infytel.InfytelUnitTesting.dto.FriendFamilyDTO;
import com.infytel.InfytelUnitTesting.dto.PlanDTO;
import com.infytel.InfytelUnitTesting.service.CustomerService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CustomerController.class)
public class CustomerControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private CustomerService customerService;
	private CustomerDTO customerDTO = null;
	private List<CustomerDTO> customers = null;
	@Before
	public void initializeCustomer() {
		customerDTO = new CustomerDTO();
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
	// Testing GET method
	@Test
	public void fetchCustomerTest() throws Exception {
		Mockito.when(customerService.fetchCustomer()).thenReturn(customers);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customers").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		ObjectMapper mapper = new ObjectMapper();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		mapper.writeValue(out, customers);
		byte[] data = out.toByteArray();
		Assert.assertEquals(new String(data), response.getContentAsString());
	}
	// Testing POST method
	@Test
	public void createCustomerTest() throws Exception {
		// setting behaviour for createCustomer of customerservice that is mocked
		Mockito.when(customerService.createCustomer(Mockito.any(CustomerDTO.class)))
				.thenReturn(customerDTO.getName() + "added successfully");
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		// Send Customer as request body to /customers
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/customers").accept(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsBytes(customerDTO)).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		Assert.assertEquals("Jackadded successfully", response.getContentAsString());
		Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
}

package com.infytel.InfytelUnitTesting.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerDTO {

	long phoneNo;
	String name;
	String email;
	int age;
	char gender;
	List<FriendFamilyDTO> friendAndFamily;
	String password;
	String address;
	PlanDTO currentPlan;
}

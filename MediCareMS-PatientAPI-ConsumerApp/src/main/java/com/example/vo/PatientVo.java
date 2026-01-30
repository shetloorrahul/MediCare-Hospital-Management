package com.example.vo;

/*
import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;*/
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PatientVo 
{
	private Integer id;
	private String name;
    private Integer age;
    private String gender;
    private Long contactno;
	private String address;
	private String problem;
	
	private DoctorVo doctorVo;

	@Override
	public String toString() {
		return "PatientVo [id=" + id + ", name=" + name + ", age=" + age + ", gender=" + gender + ", contactNo="
				+ contactno + ", address=" + address + ", problem=" + problem + "]";
	}
	
	

}

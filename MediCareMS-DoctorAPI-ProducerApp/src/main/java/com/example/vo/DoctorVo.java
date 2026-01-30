package com.example.vo;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DoctorVo
{
	
	 private Integer id;
	 private String name;
	 private String specialization;
	 private Integer experience;
	 private Long contactno;
	 private String email;
	 
}

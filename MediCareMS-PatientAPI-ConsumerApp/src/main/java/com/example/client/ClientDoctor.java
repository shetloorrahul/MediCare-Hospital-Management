package com.example.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.vo.DoctorVo;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@FeignClient("MediCareMS-DoctorAPI-ProducerApp")
public interface ClientDoctor
{
	@GetMapping("/doctor-api/getById/{id}")
     public ResponseEntity<DoctorVo> getDoctorById(@PathVariable Integer id);
	
	@GetMapping("/doctor-api/getAll")
    public ResponseEntity<List<DoctorVo>> getAllDoctors();
	

	@PostMapping("/doctor-api/register")
    public ResponseEntity<String> registerDoctor(@RequestBody DoctorVo doctor);
	
	
}

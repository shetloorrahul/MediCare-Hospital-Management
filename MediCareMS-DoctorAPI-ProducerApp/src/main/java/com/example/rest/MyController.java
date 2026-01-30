package com.example.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.IDoctorService;
import com.example.vo.DoctorVo;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/doctor-api")
@RefreshScope
@Slf4j
public class MyController 
{
	@Autowired
	private IDoctorService iDService;
	
	@GetMapping("/getAll")
	public ResponseEntity<List<DoctorVo>> getAllDoctor() throws Exception
	{
		log.info("getAll method invoked of Doctor-Producer");
		
		List<DoctorVo> allDoctor = iDService.getAllDoctor();
		
		log.info("data collected and returning responseentity");
		
		return new ResponseEntity<List<DoctorVo>>(allDoctor,HttpStatus.OK);
		
	}
	@GetMapping("/getById/{id}")
	public ResponseEntity<DoctorVo> getDoctorById(@PathVariable Integer id) throws Exception
	{
		log.info("getbyid method invoked in doctor producer ");
		
		DoctorVo doctor = iDService.getDoctorById(id);
		
		log.info("data collected and created responseentity");
		
		return new ResponseEntity<DoctorVo>(doctor,HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> registerDoctor(@RequestBody DoctorVo doctor) throws Exception
	{
		log.info("registered method invoked in doctor producer ");
		String msg = iDService.registerDoctor(doctor);
		log.info("data collected and created response entity");
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	
	//new 
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateDoctor(
	        @PathVariable Integer id,
	        @RequestBody DoctorVo doctor) throws Exception {

	    log.info("updateDoctor method invoked in doctor producer");

	    String msg = iDService.updateDoctorById(id, doctor);

	    return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteDoctor(@PathVariable Integer id) throws Exception {

	    log.info("deleteDoctor method invoked in doctor producer");

	    String msg = iDService.deleteDoctorById(id);

	    return new ResponseEntity<String>(msg, HttpStatus.OK);
	}


}

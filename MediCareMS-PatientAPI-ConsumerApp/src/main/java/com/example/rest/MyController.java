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

import com.example.client.ClientDoctor;
import com.example.service.IPatientService;
import com.example.vo.DoctorVo;
import com.example.vo.PatientVo;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/patient-api")
@RefreshScope
@Slf4j
public class MyController
{
	@Autowired
	private IPatientService service;
	
    @Autowired
    private ClientDoctor cd;
    
    
	@PostMapping("/register")
	@CircuitBreaker(name = "MediCareMS-PatientAPI-ConsumerApp",fallbackMethod = "doFallBackForDoctor")
	 public ResponseEntity<String> registerPatient(@RequestBody PatientVo patient)
	 {
		log.debug("registerPatient  begining");
		Integer id = patient.getDoctorVo().getId();
		log.debug("  Doctor Id is gathered");
		// get IPLTeam object from Target MS (IPLTeamMs)
		ResponseEntity<DoctorVo> doctorById = cd.getDoctorById(id);
		DoctorVo doctor = doctorById.getBody();
		log.debug("  MS Intra Communication is taking place");
		//set Doctor object to patient object
		patient.setDoctorVo(doctor);
		log.debug("Doctor obj is assigned to Patient object");
		//use service  to save  patient and his  doctor info
		String msg = service.registerPatient(patient);
		log.debug("Service  method is invoked");
		
		
		 return new ResponseEntity<String>(msg,HttpStatus.OK);
	 }
	@GetMapping("/getALl")
	public ResponseEntity<List<PatientVo>> getAllPatients()
	{
		log.debug("using service getALlPatientsmethod");
		List<PatientVo> allPatients = service.getAllPatients();
		log.debug("service method execution completed creating resposne entity");
		return new ResponseEntity<List<PatientVo>>(allPatients,HttpStatus.OK);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<PatientVo> getPatientById(@PathVariable Integer id) throws Exception
	{
		log.debug("using service getByIDpatientmethod");
		PatientVo patient = service.getPatientById(id);
		log.debug("service method execution completed creating resposne entity");
		return new ResponseEntity<PatientVo>(patient,HttpStatus.OK);
	}
	
	
	// Circuit Breaker Fallback method
		public    ResponseEntity<String>   doFallbackForDoctor(Exception e){
			return new ResponseEntity<String>("Problm in  Doctor Communication"+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		//new 
		@PutMapping("/update/{id}")
		public ResponseEntity<String> updatePatient(
		        @PathVariable Integer id,
		        @RequestBody PatientVo patient) throws Exception {

		    log.debug("updatePatient method invoked");

		    String msg = service.updatePatientById(id, patient);

		    return new ResponseEntity<>(msg, HttpStatus.OK);
		}
		
		@DeleteMapping("/delete/{id}")
		public ResponseEntity<String> deletePatient(@PathVariable Integer id) {

		    log.debug("deletePatient method invoked");

		    String msg = service.deleletPatientById(id);

		    return new ResponseEntity<>(msg, HttpStatus.OK);
		}


	
}

package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Doctor;
import com.example.entity.Patient;
import com.example.exception.PatientNotFoundException;
import com.example.repo.DoctorRepo;
import com.example.repo.PatientRepo;
import com.example.vo.DoctorVo;
import com.example.vo.PatientVo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PatientService implements IPatientService
{

   
	@Autowired
	private PatientRepo pRepo;
	
	@Autowired
	private DoctorRepo dRepo;
	


   

	@Override
	public List<PatientVo> getAllPatients() 
	{
		log.info("findAllPlayer() method");	
		List<Patient> all = pRepo.findAll();
		log.debug("copying List of Entities to List of VOs");

		List<PatientVo> list=new ArrayList<PatientVo>();
		
		all.forEach(patient->{
			Doctor doctor = patient.getDoctor();
			PatientVo pVo=new PatientVo();
			DoctorVo dVo=new DoctorVo();
			BeanUtils.copyProperties(patient, pVo);
			BeanUtils.copyProperties(doctor, dVo);
			pVo.setDoctorVo(dVo);
			list.add(pVo);
		});
		return list;
	}

	@Override
	public PatientVo getPatientById(Integer id) throws Exception 
	{
		log.info("findPatientById(-) method");
		Optional<Patient> byId = pRepo.findById(id);
		if(byId.isPresent())
		{
			 log.info("findPatientById(-) --patient found and returned");
			 Patient patient = byId.get();
			Doctor doctor = patient.getDoctor();
			 log.debug("copy  Entity obj to VO class obj");
			 PatientVo pVo=new PatientVo();
			 DoctorVo dVo=new DoctorVo();
			 BeanUtils.copyProperties(patient, pVo);
			 BeanUtils.copyProperties(doctor, dVo);
			 pVo.setDoctorVo(dVo);
			return  pVo;
			 
		}
		log.error("Problem in finding the patient");
		 throw new Exception("patient not found"); 

		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public String registerPatient(PatientVo patient) 
	{
		log.info("registerPatient(-) method");
		log.debug("Converting VO obj to Entity object");
		Patient entity=new Patient();
		BeanUtils.copyProperties(patient, entity,"id");
		DoctorVo doctorVo = patient.getDoctorVo();
		
		Doctor doctor = dRepo.findById(doctorVo.getId()).orElseThrow(()->new IllegalArgumentException("invalid id "));
		BeanUtils.copyProperties(doctorVo, doctor);
		
		Patient save = pRepo.save(entity);
		return "patient registred with id number "+save.getId();
//		
//		Integer id = patient.getDoctorVo().getId();
//		List<Patient> all = pRepo.findAll();
//		int count =1;
//		for(Patient p:all)
//		{
//			if(p.getDoctor().getId() == id)
//			{
//				count++;
//			}
//		}
//		
//		String msg="";
//		
//        entity.setDoctor(doctor);
//        if(count <=4)
//        {
//        	Patient save = pRepo.save(entity);
//        	msg=msg+"patient registred with id number "+save.getId();
//        }
//        else 
//        	msg=msg+"patient not registred ";
		
		
		
		//return msg;
		
		
	}
	
	//new 

	@Override
	@Transactional
	public String updatePatientById(Integer id, PatientVo patientVo) throws Exception {

	    log.info("updatePatientById(-) method");

	    Patient patient = pRepo.findById(id)
	            .orElseThrow(() -> new PatientNotFoundException("Patient not found with id : " + id));

	    // Update Patient fields
	    patient.setName(patientVo.getName());
	    patient.setAge(patientVo.getAge());
	    patient.setGender(patientVo.getGender());
	    patient.setContactno(patientVo.getContactno());
	    patient.setAddress(patientVo.getAddress());
	    patient.setProblem(patientVo.getProblem());
	    patient.setUpdatedBy(System.getProperty("user.name"));

	    // Update Doctor if provided
	    if (patientVo.getDoctorVo() != null) {
	        Integer doctorId = patientVo.getDoctorVo().getId();
	        Doctor doctor = dRepo.findById(doctorId)
	                .orElseThrow(() -> new IllegalArgumentException("Invalid Doctor Id"));
	        patient.setDoctor(doctor);
	    }

	    pRepo.save(patient);

	    return "Patient updated successfully with id : " + id;
	}

	
	@Override
	public String deleletPatientById(Integer id) {

	    log.info("deletePatientById(-) method");

	    Patient patient = pRepo.findById(id)
	            .orElseThrow(() -> new RuntimeException("Patient not found with id : " + id));
        
	    pRepo.delete(patient);

	    return "Patient deleted successfully with id : " + id;
	}

	

}

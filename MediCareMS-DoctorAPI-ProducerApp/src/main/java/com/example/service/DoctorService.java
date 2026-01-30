package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Doctor;
import com.example.exception.DoctorNotFoundException;
import com.example.repo.DoctorRepo;
import com.example.vo.DoctorVo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DoctorService implements IDoctorService
{
	@Autowired
	private DoctorRepo dRepo;

	@Override
	public List<DoctorVo> getAllDoctor() 
	{
		log.info("getAllDoctors method (service)");
		List<Doctor> all = dRepo.findAll();
		log.debug("Converting  List of Entities to List of VOs");
		List<DoctorVo> list=new ArrayList<DoctorVo>();
		for (Doctor doctor : all) 
		{
			DoctorVo vo=new DoctorVo();
			BeanUtils.copyProperties(doctor, vo);
			list.add(vo);
		}
		return list;
	}

	@Override
	public DoctorVo getDoctorById(Integer id)  throws Exception
	{
		log.info("getDoctorById(-) method (service)");
		Doctor doctor = dRepo.findById(id).orElseThrow(()->new DoctorNotFoundException("doctor not found"));
		DoctorVo vo=new DoctorVo();
		log.debug("converting Entity object to VO obj");
		BeanUtils.copyProperties(doctor, vo);
		return vo;
	}

	@Override
	public String registerDoctor(DoctorVo vo) throws Exception 
	{
		log.debug("converting  VO to Entity ");
		Doctor doctor=new Doctor();
		BeanUtils.copyProperties(vo, doctor,"id");
		doctor.setCreatedBy(System.getProperty("user.name"));
		log.info("register Doctor method (service)");
		System.out.println(doctor);
		Doctor save = dRepo.save(doctor);
		return "Doctor registered with id value : "+save.getId();
		
//		List<Doctor> all = dRepo.findAll();
//		
//		Map<String,Doctor> map=new HashMap<String,Doctor>();
//		
//		for(Doctor d:all)
//		{
//			map.put(d.getEmail(), doctor);
//		}
//		
//		String msg="";
//		if(map.containsKey(doctor.getEmail()))
//		{
//			msg=msg+"Doctor not registered";
//		}
//		else 
//			{Doctor save = dRepo.save(doctor);
//		msg=msg+"Doctor registered with id value : "+save.getId();
//			}
//		
//		
//		
		
		
		
		
		
//		String msg="";
//		for(Doctor d: all)
//		{
//			if(d.getEmail().equals(doctor.getEmail()))
//			{
//				
//				throw new Exception();
//			}
//			else 
//			{
//				Doctor save = dRepo.save(doctor);
//				msg=msg+"Doctor registered with id value : "+save.getId();
//			}
//			
//		}
		
//          return msg;
		
		
	}

	
	
	@Override
	public String updateDoctorById(Integer id, DoctorVo vo) throws Exception {
	    log.info("updateDoctorById(-) method (service)");

	    Doctor doctor = dRepo.findById(id)
	            .orElseThrow(() -> new DoctorNotFoundException("Doctor not found with id : " + id));

	    // Update fields
	    doctor.setName(vo.getName());
	    doctor.setSpecialization(vo.getSpecialization());
	    doctor.setExperience(vo.getExperience());
	    doctor.setContactno(vo.getContactno());
	    doctor.setEmail(vo.getEmail());
	    doctor.setUpdatedBy(System.getProperty("user.name"));

	    dRepo.save(doctor);

	    return "Doctor updated successfully with id : " + id;
	}

	@Override
	public String deleteDoctorById(Integer id) throws Exception {
	    log.info("deleteDoctorById(-) method (service)");

	    Doctor doctor = dRepo.findById(id)
	            .orElseThrow(() -> new DoctorNotFoundException("Doctor not found with id : " + id));

	    dRepo.delete(doctor);

	    return "Doctor deleted successfully with id : " + id;
	}

   

}

package com.example.service;

import java.util.List;
import com.example.vo.DoctorVo;

public interface IDoctorService 
{
	
	public List<DoctorVo> getAllDoctor();
	public DoctorVo getDoctorById(Integer id) throws Exception;
	public String registerDoctor(DoctorVo vo) throws Exception;
	
	
	// NEW
    public String updateDoctorById(Integer id, DoctorVo vo) throws Exception;

    // NEW
    public String deleteDoctorById(Integer id) throws Exception;

}

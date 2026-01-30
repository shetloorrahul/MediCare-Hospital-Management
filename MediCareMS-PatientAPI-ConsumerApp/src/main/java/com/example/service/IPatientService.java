package com.example.service;

import java.util.List;

import com.example.vo.PatientVo;

public interface IPatientService 
{
	public List<PatientVo> getAllPatients();
	public PatientVo getPatientById(Integer id) throws Exception;
    public String registerPatient(PatientVo patient);
    
    // NEW
    public String updatePatientById(Integer id, PatientVo patient) throws Exception;

    // EXISTING (keep)
    public String deleletPatientById(Integer id);
}

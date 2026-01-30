package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Patient;

public interface PatientRepo extends JpaRepository<Patient, Integer> 
{
	

}

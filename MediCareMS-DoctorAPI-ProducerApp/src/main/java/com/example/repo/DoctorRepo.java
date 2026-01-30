package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Doctor;

public interface DoctorRepo extends JpaRepository<Doctor, Integer>
{

}

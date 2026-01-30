package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalException 
{
	
	@ExceptionHandler(IllegalArgumentException.class)
	public  ResponseEntity<String>  handleIAE(IllegalArgumentException  iae){
		log.error("from  the  handleAE(-) method");
		 return new  ResponseEntity<String>(iae.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(DoctorNotFoundException.class)
	public  ResponseEntity<String>  handleDcotorNotFound(DoctorNotFoundException  iae){
		log.error("from  the  handleDoctorNotFound(-) method");
		 return new  ResponseEntity<String>(iae.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleAllException(Exception e)
	{
		log.error("from  the  handleAllEXception(-) method");
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

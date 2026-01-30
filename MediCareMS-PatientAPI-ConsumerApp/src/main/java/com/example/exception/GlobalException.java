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
	
	@ExceptionHandler(PatientNotFoundException.class)
	public ResponseEntity<String>  handleIAE(PatientNotFoundException iae){
		log.error(" Handling PatientNotFoundException");
		return  new ResponseEntity<String>(iae.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleAllException(Exception e)
	{
		log.error(" Handling handlingAllException");
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

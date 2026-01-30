package com.example.exception;

@SuppressWarnings("serial")
public class DoctorNotFoundException extends Exception
{

	public DoctorNotFoundException(String msg)
	{
		super(msg);
	}

	public DoctorNotFoundException()
	{
		super();
	}

	
	
}

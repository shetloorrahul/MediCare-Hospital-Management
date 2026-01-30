package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableAdminServer
@Slf4j
public class MediCareMsAdminServerApplication {

	public static void main(String[] args) 
	{
		 log.debug("start of main(-) in Admin server App");
		 SpringApplication.run(MediCareMsAdminServerApplication.class, args);
		 log.debug("end of main(-) in Admin server App");
		
	}

}

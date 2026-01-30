package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableConfigServer
@Slf4j
public class MediCareMsConfigServerApplication 
{

	public static void main(String[] args) 
	{
		log.debug("start of  main(-) method   in Config Server ");
		SpringApplication.run(MediCareMsConfigServerApplication.class, args);
		log.debug("end of  main(-) method   in Config Server ");
	}

}

package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
public class MediCareMsDoctorApiProducerAppApplication {

	public static void main(String[] args) 
	{
		log.debug("STart main(-) method  in IPLTeam Ms");
		SpringApplication.run(MediCareMsDoctorApiProducerAppApplication.class, args);
		log.debug("Endmain(-) method  in IPLPlayer Ms");
	}

}

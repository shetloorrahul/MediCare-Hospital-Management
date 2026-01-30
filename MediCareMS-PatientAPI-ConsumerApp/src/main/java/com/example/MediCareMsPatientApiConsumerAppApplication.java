package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@Slf4j
public class MediCareMsPatientApiConsumerAppApplication 
{

	public static void main(String[] args) 
	{
		log.debug("start of main(-) method in Patient service");
		SpringApplication.run(MediCareMsPatientApiConsumerAppApplication.class, args);
		log.debug("end of main(-) method in Patient service");
	}

}

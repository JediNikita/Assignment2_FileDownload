package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Assignment2FileDownloadApplication {
	
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Assignment2FileDownloadApplication.class);
    }

	public static void main(String[] args) {
		SpringApplication.run(Assignment2FileDownloadApplication.class, args);
	}

}

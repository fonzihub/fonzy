package com.alfonsosarno.fonzy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class FonzyApplication {

	public static void main(String[] args) {
		SpringApplication.run(FonzyApplication.class, args);
	}

}

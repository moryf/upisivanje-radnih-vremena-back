package com.konstil.Belezenje.radnih.vremena;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class BelezenjeRadnihVremenaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BelezenjeRadnihVremenaApplication.class, args);
	}

}

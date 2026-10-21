package com.grupo06.epicentrogourmet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class EpicentrogourmetApplication {

	public static void main(String[] args) {
		SpringApplication.run(EpicentrogourmetApplication.class, args);
	}

}

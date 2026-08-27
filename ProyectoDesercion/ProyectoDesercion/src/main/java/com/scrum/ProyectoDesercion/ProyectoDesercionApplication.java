package com.scrum.ProyectoDesercion;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProyectoDesercionApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoDesercionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("API FUNCIONANDO");
	}
}

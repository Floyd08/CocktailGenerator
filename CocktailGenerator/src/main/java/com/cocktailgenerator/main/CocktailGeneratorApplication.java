package com.cocktailgenerator.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.cocktailgenerator.controller")
public class CocktailGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CocktailGeneratorApplication.class, args);
	}

}

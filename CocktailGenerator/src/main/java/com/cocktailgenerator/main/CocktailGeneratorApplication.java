package com.cocktailgenerator.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {
		MongoAutoConfiguration.class,
		MongoDataAutoConfiguration.class
})
@ComponentScan({"com.cocktailgenerator.controller", "com.cocktailgenerator.entity", "com.cocktailgenerator.main"})
public class CocktailGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CocktailGeneratorApplication.class, args);
	}

}

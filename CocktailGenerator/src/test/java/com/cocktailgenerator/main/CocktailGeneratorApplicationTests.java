package com.cocktailgenerator.main;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.cocktailgenerator.model.Objects.Ingredient;

@SpringBootTest
@AutoConfigureMockMvc
class CocktailGeneratorApplicationTests {

	@Autowired
	private MockMvc mock;
	
	@Test
	void contextLoads() {
	}
		
}

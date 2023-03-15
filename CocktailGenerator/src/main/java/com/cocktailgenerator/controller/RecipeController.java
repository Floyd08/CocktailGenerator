package com.cocktailgenerator.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cocktailgenerator.model.Objects.DrinkGenerator;
import com.cocktailgenerator.model.Objects.Recipe;
import com.cocktailgenerator.model.Objects.RecipeBook;

import com.google.gson.Gson;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RecipeController {

	DrinkGenerator mixer;
	RecipeBook templates;
	Gson gS = new Gson();
	
	public RecipeController() {
		
		mixer = new DrinkGenerator();
		templates = null;
		
		try {
			 templates = new RecipeBook("Data/RecipeBook/Templates");
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	@GetMapping("/templates")
	public List<String> getTemplateNames() {
		
		if (templates != null) {
			//System.out.println(templates.getBookNames());
			return templates.getBookNames();
			//return new ResponseEntity<String>(gS.toJson(templates.getBookNames()), HttpStatus.OK);
		}
		else {
			return null;
		}
	}
}









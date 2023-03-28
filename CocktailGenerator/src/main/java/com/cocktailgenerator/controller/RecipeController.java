package com.cocktailgenerator.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cocktailgenerator.main.DataConnection;
import com.cocktailgenerator.model.Objects.DrinkGenerator;
import com.cocktailgenerator.model.Objects.RecipeFrontEnd;
import com.cocktailgenerator.model.Objects.Recipe;
import com.cocktailgenerator.model.Objects.RecipeBook;

import com.google.gson.Gson;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = "http://18.191.37.210")
public class RecipeController {

	DataConnection datCon;
	DrinkGenerator mixer;
	RecipeBook templates;
	Gson gS = new Gson();
	
	public RecipeController() {
		
		datCon = DataConnection.getInstance();
		mixer = new DrinkGenerator(datCon);
		templates = null;
		
		try {
			 //templates = new RecipeBook("Data/RecipeBook/Templates");
			templates = new RecipeBook(datCon);
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	@GetMapping("/templateNames")
	public List<String> getTemplateNames() {
		
		if (templates != null) {
			return templates.getBookNames();
		}
		else {
			return null;
		}
	}
	@GetMapping("/recipe")
	public String printRecipe(@RequestParam int recipeIndex) {
		
		if (templates != null) {
			return mixer.printRecipe(templates.getBook().get(recipeIndex));
		}
		else {
			return null;
		}
	}
	@GetMapping("/templates")
	public List<RecipeFrontEnd> getTemplates() {
		
		return templates.packageBook();
	}
	
	@GetMapping("/generateDrink")
	public RecipeFrontEnd generateDrink(@RequestParam int index) {
		
		Recipe newDrink = new Recipe(templates.getBook().get(index));
		newDrink = mixer.generateRecipe(newDrink);
		
		return new RecipeFrontEnd(newDrink);
	}
}









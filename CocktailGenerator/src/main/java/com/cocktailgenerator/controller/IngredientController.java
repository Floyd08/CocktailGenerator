package com.cocktailgenerator.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cocktailgenerator.data.IngredientDAO;
import com.cocktailgenerator.entity.Ingredient;
import com.cocktailgenerator.entity.RecipeBook;
import com.cocktailgenerator.main.DrinkGenRouter;
import com.cocktailgenerator.main.DrinkGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = {"${CrossOriginValue}"})
public class IngredientController {
	
	@Autowired
	private DrinkGenRouter mixer;
	
	private IngredientDAO inDAO;
	private ObjectMapper mapper;
	
	public IngredientController() {
		inDAO = new IngredientDAO();
		mapper = new ObjectMapper();
	}
	
	@GetMapping("/getUserIngredients")
	public ArrayList<Ingredient> getUserIngredients(@RequestParam String userName) {
		return mixer.getUser(userName).flattenEnumMap();
	}
	
	@GetMapping("/getAllIngredients")
	public ArrayList<Ingredient> getAllIngredients() {
		return mixer.getUser("Guest").flattenEnumMap();
	}
	
	@PostMapping("/addUserIngredient")
	public void addUserIngredient(@RequestBody String JSON) {
		
		try {
			JsonNode ingredient = mapper.readTree(JSON);
			String owner = ingredient.findValue("owner").asText();
			Ingredient newIngredient = new Ingredient(ingredient.findValue("superType").asText(),
												ingredient.findValue("type").asText(), 
												ingredient.findValue("subType").asText(), 0);
			inDAO.AddUserIngredient(newIngredient, owner);
			mixer.reinitializeUser(owner);
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	@DeleteMapping("/deleteUserIngredient")
	public void deleteUserIngredient(@RequestParam String subType, @RequestParam String owner) {
		System.out.println("Removing: " + subType);
		inDAO.removeUserIngredient(subType, owner);
		mixer.reinitializeUser(owner);
	}
}





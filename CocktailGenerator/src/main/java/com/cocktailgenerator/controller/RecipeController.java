package com.cocktailgenerator.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cocktailgenerator.data.DataConnection;
import com.cocktailgenerator.entity.Ingredient;
import com.cocktailgenerator.entity.Recipe;
import com.cocktailgenerator.entity.RecipeBook;
import com.cocktailgenerator.entity.RecipeFrontEnd;
import com.cocktailgenerator.main.DrinkGenRouter;
import com.google.gson.Gson;

@RestController
@CrossOrigin(origins = {"${CrossOriginValue}"})
public class RecipeController {

	//DrinkGenerator mixer;
	
	@Autowired
	private DrinkGenRouter mixer;
	@Autowired
	private RecipeBook templates;
	Gson gS = new Gson();
	
	public RecipeController() {
		
//		DataConnection datCon = DataConnection.getInstance();
		//mixer = new DrinkGenerator();
//		templates = null;
//		
//		try {
//			 //templates = new RecipeBook("Data/RecipeBook/Templates");
//			templates = new RecipeBook();
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			System.out.println(e.getMessage());
//		}
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
			return templates.printRecipe(templates.getBook().get(recipeIndex));
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
	public RecipeFrontEnd generateDrink(@RequestParam int index, String userName) {
		
//		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//		String sessID = attr.getRequest().getSession(true).getId(); // true == allow create
//		System.out.println("SessID=" + sessID);
		
		Recipe newDrink = new Recipe(templates.getBook().get(index));
		newDrink = mixer.getUser(userName).generateRecipe(newDrink);
		
		return new RecipeFrontEnd(newDrink);
	}
	
//	@GetMapping("/getAllIngredients")
//	public ArrayList<Ingredient> getAllIngredients(@RequestParam String userName) {
//		return mixer.getUser(userName).flattenEnumMap();
//	}
}









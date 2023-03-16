package com.cocktailgenerator.model.Objects;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import com.google.gson.Gson;

public class RecipeBook {

	private ArrayList<Recipe> book = new ArrayList<Recipe>();
	
	public RecipeBook(String filePath) throws Exception {
		
		BufferedReader bookReader = null;
		Gson gS = new Gson();
		
		try {
			
			bookReader = new BufferedReader(new FileReader(filePath));
			String Json;
			
			while ( (Json = bookReader.readLine()) != null) {
				
				book.add(gS.fromJson(Json, Recipe.class));
			}
				
			bookReader.close();
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		finally {
			if (bookReader != null)
				bookReader.close();
		}
	}
	
	public ArrayList<String> getBookNames() {
		
		ArrayList<String> bookNames = new ArrayList<String>();
		
		for (Recipe r: book) {
			bookNames.add(r.getName());
		}
		
		return bookNames;
	}

	public ArrayList<Recipe> getBook() {
		return book;
	}

	public void setBook(ArrayList<Recipe> book) {
		this.book = book;
	}
	
	public static String describeRecipe(Recipe recipe) {
		
		ArrayList<Ingredient> template = recipe.getTemplate();
		String output = "";
		
		for (int i = 0; i < template.size(); ++i) {
			
			if ( template.get(i).getType().equals("Bitters") ) {
				output += template.get(i).proportion + " dashes " + template.get(i).subType + " bitters \n";
			}
			else {
				output += template.get(i).toString() + " \n";
			}
		}
		
		if ( recipe.getExtras() != null ) {
			
			ArrayList<Ingredient> extras = recipe.getExtras();
			
			for (int i = 0; i < extras.size(); ++i) {
				output += extras.get(i).toString() + " \n";
			}
		}
			
		//System.out.println("\n" + output);
		return output;
	}
}

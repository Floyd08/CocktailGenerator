package com.cocktailgenerator.model.Objects;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;
import org.springframework.stereotype.Component;

import com.cocktailgenerator.main.DataConnection;
import com.google.gson.Gson;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

@Component
public class RecipeBook {

	private ArrayList<Recipe> book = new ArrayList<Recipe>();
	
	public RecipeBook() {
		
		DataConnection datCon = DataConnection.getInstance();
		Gson gS = new Gson();
		MongoCollection<Document> templateCOL = datCon.getDB().getCollection("Templates");
		
		FindIterable<Document> templates = templateCOL.find();		
		Iterator<Document> templIterator = templates.iterator();
		
		while(templIterator.hasNext()) {
			book.add(gS.fromJson(templIterator.next().toJson(), Recipe.class));
		}
	}
	
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
		
		for (Recipe r: this.book) {
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
	
	public List<RecipeFrontEnd> packageBook() {
		
		List<RecipeFrontEnd> newBook = new ArrayList<RecipeFrontEnd>();
		
		for (int i = 0; i < this.book.size(); ++i) {
			newBook.add( new RecipeFrontEnd(this.getBook().get(i), i) );
		}
		
		return newBook;
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
	
	public static ArrayList<String> convertForFrontEnd(Recipe recipe) {
		
		//RecipeFrontEnd output = new RecipeFrontEnd();
		ArrayList<String> output = new ArrayList<String>();
		ArrayList<Ingredient> template = recipe.getTemplate();
		
		//output.setName(recipe.getName());
		
		for (int i = 0; i < template.size(); ++i) {
			
			if ( template.get(i).getType().equals("Bitters") ) {
				output.add(template.get(i).proportion + " dashes " + template.get(i).subType + " bitters \n");
			}
			else {
				output.add(template.get(i).toString() + " \n");
			}
		}
		
		if ( recipe.getExtras() != null ) {
			
			ArrayList<Ingredient> extras = recipe.getExtras();
			
			for (int i = 0; i < extras.size(); ++i) {
				output.add(extras.get(i).toString() + " \n");
			}
		}
			
		//System.out.println("\n" + output);
		return output;
	}
}

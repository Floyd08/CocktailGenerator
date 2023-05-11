package com.cocktailgenerator.entity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

import org.bson.Document;

import com.google.gson.Gson;
import com.mongodb.client.FindIterable;

public class Ingredient {

	String superType, type, subType;
	int proportion;
	
	//default constructor
	public Ingredient() {
		
		this.superType = "Ingredient";
		this.type = "Ingredient";
		this.subType = "Ingredient";
		this.proportion = 0;
	}
 	
	public Ingredient(String superType, String type, String subType, int proportion) {
		
		this.superType = superType;
		this.type = type;
		this.subType = subType;
		this.proportion = proportion;
	}
	
	public static ArrayList<Ingredient> buildList(Iterator<Document> ingredientIterator) {
		Gson gS = new Gson();
		ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
		Document doc;
		
		while (ingredientIterator.hasNext()) {
			doc = ingredientIterator.next();
			ingredients.add( gS.fromJson(doc.toJson(), Ingredient.class) );
		}
		return ingredients;
	}
	
	public static ArrayList<Ingredient> buildList(String filePath) throws Exception {
		
		BufferedReader listReader = null;
		Gson gS = new Gson();
		
		try {
			
			ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
			listReader = new BufferedReader(new FileReader(filePath));
			String Json;
		
			while ( (Json = listReader.readLine()) != null) {
				
				ingredients.add(gS.fromJson(Json, Ingredient.class));
			}
				
			listReader.close();
			return ingredients;
		}
		
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		finally {
			if (listReader != null)
				listReader.close();
		}
		
		return null;
	}
	
	public String getSuperType() {
		return superType;
	}

	public void setSuperType(String superType) {
		this.superType = superType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public int getProportion() {
		return proportion;
	}

	public void setProportion(int proportion) {
		this.proportion = proportion;
	}
	
	@Override
	public String toString() {
		//return "Ingredient (label: " + label + ", proportion: " + proportion + ")";
		return proportion + " parts " + subType;
	}

	@Override
	public int hashCode() {
		return Objects.hash(proportion, subType, superType, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Ingredient other = (Ingredient) obj;
		return proportion == other.proportion && Objects.equals(subType, other.subType)
				&& Objects.equals(superType, other.superType) && Objects.equals(type, other.type);
	}
	
	
}

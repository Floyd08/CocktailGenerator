package com.cocktailgenerator.model.Objects;

import java.util.ArrayList;

public class RecipeFrontEnd {
	
	private int id;
	private String name;
	private ArrayList<String> descriptionRAW;
	private String description;
	
	//default constructor
	public RecipeFrontEnd() {
		
		this.id = -1;
		this.name = "default";
		this.descriptionRAW = new ArrayList<String>();
		this.description = "Empty Description";
	}
	
	public RecipeFrontEnd(Recipe r) {
		
		this.id = -1;
		this.name = r.getName();
		this.descriptionRAW = RecipeBook.convertForFrontEnd(r);
		this.description = RecipeBook.describeRecipe(r);
	}
	
	public RecipeFrontEnd(Recipe r, int i) {
		
		this.id = i;
		this.name = r.getName();
		this.descriptionRAW = RecipeBook.convertForFrontEnd(r);
		this.description = RecipeBook.describeRecipe(r);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getDescriptionRAW() {
		return descriptionRAW;
	}

	public void setDescriptionRAW(ArrayList<String> descriptionRAW) {
		this.descriptionRAW = descriptionRAW;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "RecipeFrontEnd [id=" + id + ", name=" + name + ", descriptionRAW=" + descriptionRAW + ", description="
				+ description + "]";
	}

	
	
	
}

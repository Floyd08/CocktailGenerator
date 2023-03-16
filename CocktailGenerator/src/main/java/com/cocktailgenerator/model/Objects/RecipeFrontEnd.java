package com.cocktailgenerator.model.Objects;

public class RecipeFrontEnd {
	
	private int id;
	private String name;
	private String description;
	
	//default constructor
	public RecipeFrontEnd() {
		
		this.id = -1;
		this.name = "default";
		this.description = "empty description";
	}
	
	public RecipeFrontEnd(Recipe r) {
		
		this.id = -1;
		this.name = r.getName();
		this.description = RecipeBook.describeRecipe(r);
	}
	
	public RecipeFrontEnd(Recipe r, int i) {
		
		this.id = i;
		this.name = r.getName();
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "DrinkTemplateFrontEnd [id=" + id + ", name=" + name + ", description=" + description + "]";
	}
	
	
}

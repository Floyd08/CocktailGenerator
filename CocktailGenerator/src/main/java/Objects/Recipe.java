package Objects;

import java.util.ArrayList;

import com.google.gson.Gson;

public class Recipe {

	private String name;
	private ArrayList<Ingredient> template;
	private ArrayList<Ingredient> extras;
	private boolean shaken, stirred;
	
	public Recipe(String name, ArrayList<Ingredient> template) {
		
		this.name = name;
		this.template = template;
		this.extras = new ArrayList<Ingredient>();
	}
	
	public Recipe(String name, ArrayList<Ingredient> template, ArrayList<Ingredient> extras) {
		
		this.name = name;
		this.template = template;
		this.extras = extras;
	}
	// Copy constructor
	public Recipe(Recipe toCopy) {
		
		this.name = new String(toCopy.name);
		this.template = new ArrayList<Ingredient>(toCopy.template);
		this.extras = new ArrayList<Ingredient>(toCopy.extras);
		this.shaken = toCopy.shaken;
		this.stirred = toCopy.stirred;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Ingredient> getTemplate() {
		return template;
	}

	public void setTemplate(ArrayList<Ingredient> template) {
		this.template = template;
	}
	
	public ArrayList<Ingredient> getExtras() {
		return extras;
	}

	public void setExtras(ArrayList<Ingredient> extras) {
		this.extras = extras;
	}
	
	public static Recipe fromJSON(String Json) {
		
		Gson gS = new Gson();
		return gS.fromJson(Json, Recipe.class);
	}
	
	public String toJSON() {
		
		Gson gS = new Gson();
		return gS.toJson(this);
	}
	
}

package Objects;

import java.util.ArrayList;

public class Recipe {

	private String name;
	private ArrayList<Ingredient> template;
	private ArrayList<Ingredient> extras;
	private boolean shaken, stirred;
	
	public Recipe(String name, ArrayList<Ingredient> template) {
		
		this.name = name;
		this.template = template;
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
}

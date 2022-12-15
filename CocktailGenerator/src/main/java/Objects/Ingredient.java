package Objects;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Objects;

public abstract class Ingredient {

	String label;
	int proportion;
	
	//default constructor
	public Ingredient() {
		
		this.label = "undefined";
		this.proportion = 0;
	}
 	
	public Ingredient(String label, int proportion) {
		
		this.label = label;
		this.proportion = proportion;
	}
	
	public static ArrayList<? extends Ingredient> buildList(String filePath) throws Exception {
		
		ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
		return ingredients;
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
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
		return proportion + " parts " + label;
	}

	@Override
	public int hashCode() {
		return Objects.hash(label, proportion);
	}

	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		
		if (!(obj instanceof Ingredient))
			return false;
		
		Ingredient other = (Ingredient) obj;
		
		return Objects.equals(label, other.label) && proportion == other.proportion;
	}

	
}

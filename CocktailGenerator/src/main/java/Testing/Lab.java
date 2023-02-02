package Testing;

import java.util.ArrayList;

import com.google.gson.Gson;

import Objects.Ingredient;
import Objects.Recipe;

//import Objects.Spirit;

public class Lab {

	public static void main(String[] args) {
		
		Gson gS = new Gson();
		
		ArrayList<Ingredient> sideCar = new ArrayList<Ingredient>();
		sideCar.add(new Ingredient("Spirit", "Spirit", "Cognac", 8));
		sideCar.add(new Ingredient("Liqueur", "FruitLiqueur", "Curacao", 2));
		sideCar.add(new Ingredient("Juice", "TartJuice", "Lemon", 1));
		
		Recipe drank = new Recipe("SideCar", sideCar);
		String Json = gS.toJson(drank);
		
		System.out.println(Json);

	}

}

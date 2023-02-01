package Testing;

import java.util.ArrayList;

import Objects.Ingredient;

public class GeneralTests {

	public static void main(String[] args) throws Exception {
		
		ArrayList<Ingredient> spirits = Ingredient.buildList("Data/MyInventory/Spirits");
		
		System.out.println(spirits.toString());
	}

}

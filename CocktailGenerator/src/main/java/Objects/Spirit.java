package Objects;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Spirit extends Ingredient {

	public Spirit(int proportion) {
		super("placeHolder", proportion);
	}
	
	public Spirit(String label, int proportion) {
		super(label, proportion);
	}
	
	public static ArrayList<Spirit> buildList() {
		
		ArrayList<Spirit> spirits = new ArrayList<Spirit>();
		spirits.add(new Spirit("Whiskey", 0));
		spirits.add(new Spirit("Cognac", 0));
		spirits.add(new Spirit("Dark Rum", 0));
		spirits.add(new Spirit("White Rum", 0));
		spirits.add(new Spirit("Tequila", 0));
		spirits.add(new Spirit("Gin", 0));
		spirits.add(new Spirit("Vodka", 0));
		
		return spirits;
	}
	
	public static ArrayList<Spirit> buildList(String filePath) {
		
		try {
			
			ArrayList<Spirit> spirits = new ArrayList<Spirit>();
			BufferedReader listReader = new BufferedReader(new FileReader(filePath));
			String name;
		
			while ( (name = listReader.readLine()) != null)
				spirits.add(new Spirit(name, 0));
		
			return spirits;
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		return null;
	}
}








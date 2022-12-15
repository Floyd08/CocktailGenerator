package Objects;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Syrup extends Ingredient {

	public Syrup(int proportion) {
		super("placeHolder", proportion);
	}
	
	public Syrup(String label, int proportion) {
		super(label, proportion);
	}
	
	public static ArrayList<Syrup> buildList(String filePath) {
		
		try {
			
			ArrayList<Syrup> syrups = new ArrayList<Syrup>();
			BufferedReader listReader = new BufferedReader(new FileReader(filePath));
			String name;
		
			while ( (name = listReader.readLine()) != null)
				syrups.add(new Syrup(name, 0));
		
			return syrups;
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		return null;
	}
}

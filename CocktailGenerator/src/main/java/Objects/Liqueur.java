package Objects;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Liqueur extends Ingredient {

	public Liqueur(int proportion) {
		super("placeHolder", proportion);
	}
	
	public Liqueur(String label, int proportion) {
		super(label, proportion);
	}
	
	public static ArrayList<Liqueur> buildList() {
		
		ArrayList<Liqueur> liqueurs = new ArrayList<Liqueur>();
		liqueurs.add(new Liqueur("Curacao", 0));
		liqueurs.add(new Liqueur("Peach Schnapps", 0));
		liqueurs.add(new Liqueur("Cherry Heering", 0));
		liqueurs.add(new Liqueur("Mango Malibu", 0));
		liqueurs.add(new Liqueur("Sloe Gin", 0));
		liqueurs.add(new Liqueur("Raspberry Wine", 0));
		liqueurs.add(new Liqueur("Creme de Cacao", 0));
		liqueurs.add(new Liqueur("Rhubarb Liqueur", 0));
		liqueurs.add(new Liqueur("Southern Comfort", 0));
		
		return liqueurs;
	}
	
	public static ArrayList<Liqueur> buildList(String filePath) {
		
		try {
			
			ArrayList<Liqueur> liqueurs = new ArrayList<Liqueur>();
			BufferedReader listReader = new BufferedReader(new FileReader(filePath));
			String name;
		
			while ( (name = listReader.readLine()) != null)
				liqueurs.add(new Liqueur(name, 0));
		
			return liqueurs;
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		return null;
	}
}







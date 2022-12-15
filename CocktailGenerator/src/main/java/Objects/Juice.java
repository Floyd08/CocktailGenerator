package Objects;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Juice extends Ingredient {

	public Juice(int proportion) {
		super("placeHolder", proportion);
	}
	
	public Juice(String label, int proportion) {
		super(label, proportion);
	}
	
	public static ArrayList<Juice> buildList() {
		
		ArrayList<Juice> juices = new ArrayList<Juice>();
		juices.add(new Juice("Lemon", 0));
		juices.add(new Juice("Lime", 0));
		juices.add(new Juice("Cranberry", 0));
		juices.add(new Juice("Orange", 0));
		juices.add(new Juice("Apple", 0));
		juices.add(new Juice("Peach", 0));
		juices.add(new Juice("Pineapple", 0));
		
		return juices;
	}
	
	public static ArrayList<Juice> buildList(String filePath) {
		
		try {
			
			ArrayList<Juice> juices = new ArrayList<Juice>();
			BufferedReader listReader = new BufferedReader(new FileReader(filePath));
			String name;
		
			while ( (name = listReader.readLine()) != null)
				juices.add(new Juice(name, 0));
		
			return juices;
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		return null;
	}
}

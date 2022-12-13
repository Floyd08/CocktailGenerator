package Objects;

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
}

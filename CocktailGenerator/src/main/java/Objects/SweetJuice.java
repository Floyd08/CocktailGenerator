package Objects;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class SweetJuice extends Juice {

	public SweetJuice(int proportion) {
		super("placeHolder", proportion);
	}
	
	public SweetJuice(String label, int proportion) {
		super(label, proportion);
	}
	
	public static ArrayList<SweetJuice> buildSweetJuiceList(String filePath) {
		
		try {
			
			ArrayList<SweetJuice> sweetJuices = new ArrayList<SweetJuice>();
			BufferedReader listReader = new BufferedReader(new FileReader(filePath));
			String name;
		
			while ( (name = listReader.readLine()) != null)
				sweetJuices.add(new SweetJuice(name, 0));
		
			return sweetJuices;
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		return null;
	}
}

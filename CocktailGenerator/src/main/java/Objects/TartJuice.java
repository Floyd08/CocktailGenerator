package Objects;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class TartJuice extends Juice {

	public TartJuice(int proportion) {
		super("placeHolder", proportion);
	}
	
	public TartJuice(String label, int proportion) {
		super(label, proportion);
	}
	
	public static ArrayList<TartJuice> buildTartJuiceList(String filePath) {
		
		try {
			
			ArrayList<TartJuice> tartJuices = new ArrayList<TartJuice>();
			BufferedReader listReader = new BufferedReader(new FileReader(filePath));
			String name;
		
			while ( (name = listReader.readLine()) != null)
				tartJuices.add(new TartJuice(name, 0));
		
			return tartJuices;
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		return null;
	}
}

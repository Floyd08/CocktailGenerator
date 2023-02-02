package Objects;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import com.google.gson.Gson;

public class RecipeBook {

	private ArrayList<Recipe> book = new ArrayList<Recipe>();
	
	public RecipeBook(String filePath) throws Exception {
		
		BufferedReader bookReader = null;
		Gson gS = new Gson();
		
		try {
			
			bookReader = new BufferedReader(new FileReader(filePath));
			String Json;
			
			while ( (Json = bookReader.readLine()) != null) {
				
				book.add(gS.fromJson(Json, Recipe.class));
			}
				
			bookReader.close();
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		finally {
			if (bookReader != null)
				bookReader.close();
		}
	}

	public ArrayList<Recipe> getBook() {
		return book;
	}

	public void setBook(ArrayList<Recipe> book) {
		this.book = book;
	}
}

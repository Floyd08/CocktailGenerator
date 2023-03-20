package com.cocktailgenerator.main;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class DataConnection {

	//private String uri = "mongodb://localhost:27017";
	private String uri = "mongodb+srv://Milkpants:YgDzEFw5klPpAyW2@cluster0.jhwkbgk.mongodb.net/?retryWrites=true&w=majority";
	private static DataConnection datCon;
	private MongoDatabase db;
	
	private DataConnection() {
		MongoClient mClient = MongoClients.create(uri);
		db = mClient.getDatabase("CocktailGenerator");
	}
	
	public static DataConnection getInstance() {
		if (datCon == null) {
			datCon = new DataConnection();
		}
		return datCon;
	}
	
	public MongoDatabase getDB() {
		return db;
	}
}

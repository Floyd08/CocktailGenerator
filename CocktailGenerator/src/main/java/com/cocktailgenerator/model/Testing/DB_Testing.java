package com.cocktailgenerator.model.Testing;

import java.util.Iterator;

import org.bson.Document;

import com.cocktailgenerator.main.DataConnection;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class DB_Testing {

	public static void main(String[] args) {
		
		DataConnection datCon = DataConnection.getInstance();
		MongoCollection<Document> templateJSONs = datCon.getDB().getCollection("Templates");
		
		FindIterable<Document> templates = templateJSONs.find();		
		Iterator<Document> templIterator = templates.iterator();
		
		while (templIterator.hasNext()) {
			System.out.println(templIterator.next());
		}
	}
}

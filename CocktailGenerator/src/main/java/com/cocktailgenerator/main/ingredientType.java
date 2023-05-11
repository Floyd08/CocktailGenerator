package com.cocktailgenerator.main;

public enum ingredientType {
	Spirit, Liqueur, FruitLiqueur, HerbalLiqueur, BitterLiqueur,
		DessertLiqueur, AromatizedWine, Juice, TartJuice, 
		SweetJuice, Syrup, Bitters;
		
		private static ingredientType[] type = ingredientType.values();
		
		public static ingredientType getType(int index) {
			return type[index];
		}
}

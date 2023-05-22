package com.cocktailgenerator.main;

public enum ingredientType {
	Spirit, Liqueur, FruitLiqueur, HerbalLiqueur, BitterLiqueur,
		DessertLiqueur, AromatizedWine, Juice, TartJuice, 
		SweetJuice, Syrup, Bitters;
		
		private static ingredientType[] type = ingredientType.values();
		private static ingredientType[] superTypes = {ingredientType.Spirit, ingredientType.Liqueur, 
												ingredientType.AromatizedWine, ingredientType.Juice, 
												ingredientType.Syrup, ingredientType.Bitters};
		
		public static ingredientType getType(int index) {
			return type[index];
		}
		
		public static ingredientType[] getSuperTypes() {
			return superTypes;
		}
}

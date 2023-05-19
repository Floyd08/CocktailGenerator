export interface ingredient {
	superType: string;
	type: string;
	subType: string;
	proportion: string;
	toggle: boolean;		//used by the the inventory-view to determine if an ingredient should be added or remoeved
}

function copy(ingredient: ingredient): ingredient {
	let newIngredient = Object.create(ingredient);

	newIngredient.superType = ingredient.superType;
	newIngredient.type = ingredient.type;
	newIngredient.subType = ingredient.subType;
	newIngredient.toggle = ingredient.toggle;

	return newIngredient
}
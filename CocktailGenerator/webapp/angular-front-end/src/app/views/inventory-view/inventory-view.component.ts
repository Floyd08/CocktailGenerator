import { Component, OnInit } from '@angular/core';
import { ingredient } from 'src/app/interfaces/ingredient.interface';
import { IngredientService } from 'src/app/services/ingredient.service';

@Component({
  selector: 'app-inventory-view',
  templateUrl: './inventory-view.component.html',
  styleUrls: ['./inventory-view.component.css']
})
export class InventoryViewComponent implements OnInit {

	private allIngredients: ingredient[];

	constructor(private IS: IngredientService) {
		this.allIngredients = [];
	}

	ngOnInit(): void {
		this.IS.getAll().subscribe(data => {
			for( let i = 0; i < data.length; ++i){
				this.allIngredients.push(data[i]);
				// this.allIngredients.push(
				// 	this.fromJson(data[i])
				// )
			}
		})
	}

	// fromJson(json: string): ingredient {

	// 	let newIngredient!: ingredient;
	   
	// 	var obj = JSON.parse(json);
	// 	newIngredient.superType = obj.superType;
	// 	newIngredient.type = obj.type;
	// 	newIngredient.subType = obj.type;
	// 	newIngredient.proportion = obj.proportion;
	
	// 	return newIngredient;
	// }
}

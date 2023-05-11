import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs/internal/Subscription';
import { ingredient } from 'src/app/interfaces/ingredient.interface';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { IngredientService } from 'src/app/services/ingredient.service';

@Component({
  selector: 'app-inventory-view',
  templateUrl: './inventory-view.component.html',
  styleUrls: ['./inventory-view.component.css']
})
export class InventoryViewComponent implements OnInit {

	allIngredients: ingredient[];
	userName!: string;
	userNameSubscription!: Subscription;

	constructor(private IS: IngredientService, private AS: AuthenticationService) {
		this.allIngredients = [];
	}

	ngOnInit(): void {
		this.userNameSubscription = this.AS.currentMessage.subscribe(message => this.userName = message)
		this.IS.getAll(this.userName).subscribe(data => {
			for( let i = 0; i < data.length; ++i){
				this.allIngredients.push(data[i]);
				// this.allIngredients.push(
				// 	this.fromJson(data[i])
				// )
			}
			console.log(this.allIngredients[0]);
		})
		this.userNameSubscription = this.AS.currentMessage.subscribe(message => this.userName = message)		
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

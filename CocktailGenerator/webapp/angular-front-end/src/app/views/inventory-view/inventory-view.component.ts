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
	userIngredients: ingredient[];
	userName!: string;
	userNameSubscription!: Subscription;
	ingredientCopy!: ingredient;

	constructor(private IS: IngredientService, private AS: AuthenticationService) {
		this.allIngredients = [];
		this.userIngredients = [];
	}

	ngOnInit(): void {

		this.userNameSubscription = this.AS.currentMessage.subscribe(message => this.userName = message)
		this.IS.getAll().subscribe(data => {
			for( let i = 0; i < data.length; ++i){
				this.allIngredients.push(data[i]);
				this.allIngredients[i].toggle = false;
			}
		})
		this.IS.getUserIngredients(this.userName).subscribe(data => {
			for( let i = 0; i < data.length; ++i){
				this.userIngredients.push(data[i]);
				this.userIngredients[i].toggle = true;
			}
		})
	}

	public toggleIngredientMaster(index: number) {
		this.allIngredients[index].toggle = !this.allIngredients[index].toggle;
		//console.log(this.allIngredients[index]);
	}

	public toggleIngredientUser(index: number) {
		this.userIngredients[index].toggle = !this.userIngredients[index].toggle;
		console.log(this.userIngredients);
	}

	public addCheckedIngredients(){
		for (let i = 0; i < this.allIngredients.length; ++i) {
			if (this.allIngredients[i].toggle) {
				this.userIngredients.push(this.copyIngredient(this.allIngredients[i]));
				this.IS.add(this.allIngredients[i], this.userName);
			}
		}
	}

	public removeCheckedIngredients(){

		for(let i = 0; i < this.userIngredients.length; ++i) {
			if (!this.userIngredients[i].toggle) {
				this.IS.remove(this.userIngredients[i].subType, this.userName);
			}
		}
		let newList = this.userIngredients.filter(this.checkRemove);
		this.userIngredients = newList;
	}

	public checkRemove(ingredient: ingredient): boolean{
		console.log(ingredient);
		if (!ingredient.toggle) {
			return ingredient.toggle;
		}
		else {
			return ingredient.toggle;
		}
	}

	public copyIngredient(ingredient: ingredient): ingredient {
		let newIngredient = Object.create(ingredient);
	
		newIngredient.superType = ingredient.superType;
		newIngredient.type = ingredient.type;
		newIngredient.subType = ingredient.subType;
		newIngredient.toggle = ingredient.toggle;
	
		return newIngredient
	}
}

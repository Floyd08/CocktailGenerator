import { Component, OnInit, ViewChild } from '@angular/core';
import { Observable, Subscription } from 'rxjs';
import { RecipeService } from '../services/recipe-service.service';
import { DrinkTemplate } from '../model/DrinkTemplate';
import { LoginService } from '../services/login.service';
import { AuthenticationService } from '../services/authentication.service';

interface drinkName {
	index: number;
	name: string;
}

@Component({
  selector: 'app-drink-view',
  templateUrl: './drink-view.component.html',
  styleUrls: ['./drink-view.component.css']
})
export class DrinkViewComponent implements OnInit {
	
	templateNames: drinkName[];
	drinkTemplates: DrinkTemplate[];
	templateSelect: string;
	newDrink: DrinkTemplate;
	userName!: string;
	userNameSubscription!: Subscription;

	@ViewChild('templateSelect') dropDown: any;

	selectedNum = 0;
	
	constructor(private RS: RecipeService, private AS: AuthenticationService) {
		this.templateNames = []; 
		this.drinkTemplates = [];
		this.templateSelect = "";
		this.newDrink = {
			id: 0, 
			name: "",
			descriptionRAW: [],
			description: ""
		}
	}
	
	ngOnInit() {		
		this.RS.getDrinkTemplates().subscribe(data => {
			this.drinkTemplates = data;
			this.populateNames();
			//this.generateDrink();
		})
		this.userNameSubscription = this.AS.currentMessage.subscribe(message => this.userName = message);
	}
	
	populateNames() {
		for(let i = 0; i < this.drinkTemplates.length; ++i){
			const newName = {
				index: i,
				name: this.drinkTemplates[i].name
			}
			type drinkName = typeof newName;
			this.templateNames[i] = newName;
		}
	}

	generateDrink() {
		this.RS.generateNewDrink(this.selectedNum, this.userName).subscribe(data => {
			this.newDrink = data;
			//this.convertToOunces();
			//console.log(this.newDrink.description);
		})
	}

	updateDrinkDisplay() {
		for(let i = 0; i < this.newDrink.description.length; ++i) {
			
		}
	}

	toggleDropDown() {
		this.dropDown.close();
	}

	// useAsGuest() {
	// 	this.RS.logInGuest();
	//   }

	// convertToOunces() {
	// 	this.newDrink.description = this.newDrink.description.replaceAll("parts", "oz");
		
	// 	let newMeasure: number = parseInt(this.newDrink.description[0]) *.25;
	// }

}

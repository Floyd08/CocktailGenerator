import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { RecipeService } from '../services/recipe-service.service';
import { DrinkTemplate } from '../model/DrinkTemplate';

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
	
	//templateNames: string[];			//populate this array from the backend
	templateNames: drinkName[];
	drinkTemplates: DrinkTemplate[];
	templateSelect: string;
	newDrink!: DrinkTemplate;

	selectedNum = 0;
	
	constructor(private RS: RecipeService) {
		this.templateNames = []; 
		this.drinkTemplates = [];
		this.templateSelect = "";
	}
	
	ngOnInit() {		
		this.RS.getDrinkTemplates().subscribe(data => {
			this.drinkTemplates = data;
			this.populateNames();
			this.generateDrink();
		})
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
		this.RS.generateNewDrink(this.selectedNum).subscribe(data => {
			this.newDrink = data;
			console.log(this.newDrink.description);
		})
	}

}

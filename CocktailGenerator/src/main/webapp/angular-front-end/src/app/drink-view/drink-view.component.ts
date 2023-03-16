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
	
	constructor(private RS: RecipeService) {
		this.templateNames = []; 
		this.drinkTemplates = [];
		this.templateSelect = "";
	}
	
	ngOnInit() {		
		this.RS.getDrinkTemplates().subscribe(data => {
			this.drinkTemplates = data;
			this.populateNames();
			//console.log(this.templateNames[0]);
			//console.log(this.drinkTemplates);
			//this.setTemplateDescription(0);
		})
		
		//console.log(this.templateNames[0]);		
		
		//this.RS.getRecipe(0).subscribe(data => {
			//this.templateSelect = data;
			//console.log(this.templateSelect);
		//})
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
	
	setTemplateDescription(index: number): string {
		this.templateSelect = this.drinkTemplates[index].description;
		return this.templateSelect;
	}
	
	
	//getRecipe(index: number) {
		//this.RS.getRecipe(index).subscribe(data => {
			//this.templateSelect = data;
			//console.log(this.templateSelect);
			//document.getElementById("recipeView")!.innerHTML = this.templateSelect;
		//})	
	//}

}

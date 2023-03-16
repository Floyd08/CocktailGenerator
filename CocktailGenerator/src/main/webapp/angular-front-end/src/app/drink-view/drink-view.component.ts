import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { RecipeService } from '../services/recipe-service.service';
import { DrinkTemplate } from '../model/DrinkTemplate';

//interface Template {
	//value: number;
	//viewValue: string;
//}

@Component({
  selector: 'app-drink-view',
  templateUrl: './drink-view.component.html',
  styleUrls: ['./drink-view.component.css']
})
export class DrinkViewComponent implements OnInit {
	
	templateNames: string[];			//populate this array from the backend
	drinkTemplates: DrinkTemplate[];
	templateSelect: string;
	
	constructor(private RS: RecipeService) {
		this.templateNames = []; 
		this.drinkTemplates = [];
		this.templateSelect = "";
	}
	
	ngOnInit() {
		this.RS.getNames().subscribe(data => {
			this.templateNames = data;
			//console.log(this.templateNames);
		})
		
		this.RS.getDrinkTemplates().subscribe(data => {
			this.drinkTemplates = data;
			//console.log(this.drinkTemplates);
			this.setTemplateDescription(0);
		})
		
		
		
		//this.RS.getRecipe(0).subscribe(data => {
			//this.templateSelect = data;
			//console.log(this.templateSelect);
		//})
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

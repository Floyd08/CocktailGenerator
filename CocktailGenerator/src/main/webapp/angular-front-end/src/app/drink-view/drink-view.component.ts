import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { RecipeService } from '../services/recipe-service.service';

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
	
	templateNames: string[] = [];			//populate this array from the backend
	templateSelect: string = "";
	
	constructor(private RS: RecipeService) {}
	
	ngOnInit() {
		this.RS.getNames().subscribe(data => {
			this.templateNames = data;
			//console.log(this.templateNames);
		})
		this.RS.getRecipe(0).subscribe(data => {
			this.templateSelect = data;
			//console.log(this.templateSelect);
		})
	}
	
	getRecipe(index: number) {
		this.RS.getRecipe(index).subscribe(data => {
			this.templateSelect = data;
			//console.log(this.templateSelect);
			document.getElementById("recipeView")!.innerHTML = this.templateSelect;
		})
		
		
	}

}

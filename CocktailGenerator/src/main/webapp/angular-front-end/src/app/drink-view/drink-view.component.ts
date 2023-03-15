import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { RecipeService } from '../services/recipe-service.service';

interface Template {
	value: number;
	viewValue: string;
}

@Component({
  selector: 'app-drink-view',
  templateUrl: './drink-view.component.html',
  styleUrls: ['./drink-view.component.css']
})
export class DrinkViewComponent implements OnInit {
	
	templateNames: string[] = [];			//populate this array from the backend
	
	constructor(private RS: RecipeService) {}
	
	ngOnInit() {
		this.RS.getNames().subscribe(data => {
			this.templateNames = data;
			console.log(this.templateNames);
		})
		
		//this.templateNames = this.RS.getNames;
	}

}

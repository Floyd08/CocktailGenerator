import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DrinkTemplate } from '../model/DrinkTemplate';

@Injectable({
  	providedIn: 'root'
})
export class RecipeService {

	private templatesUrl: string;
	private templateNamesUrl: string;
	private recipeUrl: string;
	private newDrinkUrl: string;

  	constructor(private http: HttpClient) {
		this.templatesUrl = 'http://localhost:8080/templates';
		this.templateNamesUrl = 'http://localhost:8080/templateNames';
		this.recipeUrl = 'http://localhost:8080/recipe';
		this.newDrinkUrl = 'http://localhost:8080/generateDrink'
	}
	
	public getNames(): Observable<string[]> {
		return this.http.get<string[]>(this.templateNamesUrl);
	}
	
	public getRecipe(index: number): Observable<string> {
		const params = new HttpParams().append('recipeIndex', index);
		const header = new HttpHeaders().append('responseType', 'text');
		return this.http.get(this.recipeUrl, {responseType: 'text', params});
	}

	public generateNewDrink(index: number): Observable<DrinkTemplate> {
		const params = new HttpParams().append('index', index);
		return this.http.get<DrinkTemplate>(this.newDrinkUrl, {params});
	}
	
	public getDrinkTemplates(): Observable<DrinkTemplate[]> {
		return this.http.get<DrinkTemplate[]>(this.templatesUrl);
	}
}

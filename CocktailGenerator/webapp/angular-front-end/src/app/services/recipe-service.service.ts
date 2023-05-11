import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DrinkTemplate } from '../model/DrinkTemplate';
import { environment } from '../../environments/environment';

@Injectable({
  	providedIn: 'root'
})
export class RecipeService {

	private templatesUrl: string;
	private templateNamesUrl: string;
	//private recipeUrl: string;
	private newDrinkUrl: string;
	private backendUrl: string;
	private guestUrl: string;

  	constructor(private http: HttpClient) {
		this.backendUrl = 'http://localhost:8080';
		//this.backendUrl = 'http://18.191.37.210:8080';
		//this.backendUrl = environment.apiURL;

		this.templatesUrl = this.backendUrl + '/templates';
		this.templateNamesUrl = this.backendUrl + '/templateNames';
		//this.recipeUrl = this.backendUrl + '/recipe';
		this.newDrinkUrl = this.backendUrl + '/generateDrink'
		this.guestUrl = this.backendUrl + '/loadGuest';
	}
	
	public getNames(): Observable<string[]> {
		return this.http.get<string[]>(this.templateNamesUrl);
	}
	
	// public getRecipe(index: number): Observable<string> {
	// 	const params = new HttpParams().append('recipeIndex', index);
	// 	const header = new HttpHeaders().append('responseType', 'text');
	// 	return this.http.get(this.recipeUrl, {responseType: 'text', params});
	// }

	public generateNewDrink(index: number, userName: string): Observable<DrinkTemplate> {
		const params = new HttpParams().append('index', index).append('userName', userName);
		return this.http.get<DrinkTemplate>(this.newDrinkUrl, {params});
	}
	
	public getDrinkTemplates(): Observable<DrinkTemplate[]> {
		return this.http.get<DrinkTemplate[]>(this.templatesUrl);
	}

	// public logInGuest() {
	// 	this.http.get(this.guestUrl).subscribe();
	// }
}

import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  	providedIn: 'root'
})
export class RecipeService {

	private templateUrl: string;
	private recipeUrl: string;

  	constructor(private http: HttpClient) {
		this.templateUrl = 'http://localhost:8080/templates';
		this.recipeUrl = 'http://localhost:8080/recipe';
	}
	
	public getNames(): Observable<string[]> {
		return this.http.get<string[]>(this.templateUrl);
	}
	
	public getRecipe(index: number): Observable<string> {
		const params = new HttpParams().append('recipeIndex', index);
		const header = new HttpHeaders().append('responseType', 'text');
		return this.http.get(this.recipeUrl, {responseType: 'text', params});
	}
}

import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { ingredient } from '../interfaces/ingredient.interface';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs/internal/Observable';

@Injectable({
	providedIn: 'root'
})
export class IngredientService {

	private allIngredientsUrl: string;
	private userIngredientsUrl: string;
	private addUrl: string;
	private removeUrl: string;
	private backendUrl: string;

	constructor(private http: HttpClient) { 

		this.backendUrl = 'http://localhost:8080';
		//this.backendUrl = environment.apiURL;
		this.allIngredientsUrl = this.backendUrl + '/getAllIngredients';
		this.userIngredientsUrl = this.backendUrl + '/getUserIngredients';
		this.addUrl = this.backendUrl + '/addUserIngredient';
		this.removeUrl = this.backendUrl + '/deleteUserIngredient';
	}

	public getUserIngredients(userName: string): Observable<ingredient[]> {
		const params = new HttpParams().append('userName', userName);
		return this.http.get<ingredient[]>(this.userIngredientsUrl, {params});
	}

	public getAll(): Observable<ingredient[]> {
		return this.http.get<ingredient[]>(this.allIngredientsUrl);
	}

	public add(ingredient: ingredient, userName: string) {
		
		var ingredientJSON = {"owner": userName,
							"superType": ingredient.superType,
							"type": ingredient.type,
							"subType": ingredient.subType}
		this.http.post(this.addUrl, ingredientJSON).subscribe();
	}

	public remove(subType: string, userName: string) {
		const params = new HttpParams().append('subType', subType).append('owner', userName);
		this.http.delete(this.removeUrl, {params}).subscribe();
	}
}





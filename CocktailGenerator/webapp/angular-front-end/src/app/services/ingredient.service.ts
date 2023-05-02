import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ingredient } from '../interfaces/ingredient.interface';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs/internal/Observable';

@Injectable({
	providedIn: 'root'
})
export class IngredientService {

	private allIngredientsUrl: string;
	private backendUrl: string;

	constructor(private http: HttpClient) { 

		this.backendUrl = environment.apiURL;
		this.allIngredientsUrl = this.backendUrl + '/getAllIngredients';
	}

	public getAll(): Observable<ingredient[]> {
		return this.http.get<ingredient[]>(this.allIngredientsUrl);
	}
}

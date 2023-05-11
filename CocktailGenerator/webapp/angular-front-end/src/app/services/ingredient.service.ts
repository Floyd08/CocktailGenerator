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
	private guestUrl: string;
	private backendUrl: string;

	constructor(private http: HttpClient) { 

		this.backendUrl = 'http://localhost:8080';
		//this.backendUrl = environment.apiURL;
		this.allIngredientsUrl = this.backendUrl + '/getAllIngredients';
		this.guestUrl = this.backendUrl + '/loadGuest';
	}

	public getAll(userName: string): Observable<ingredient[]> {
		const params = new HttpParams().append('userName', userName);
		return this.http.get<ingredient[]>(this.allIngredientsUrl, {params});
	}
}

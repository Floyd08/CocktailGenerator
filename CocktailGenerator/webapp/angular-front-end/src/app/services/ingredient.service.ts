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

		this.backendUrl = environment.apiURL;
		this.allIngredientsUrl = this.backendUrl + '/getAllIngredients';
		this.userIngredientsUrl = this.backendUrl + '/getUserIngredients';
		this.addUrl = this.backendUrl + '/addUserIngredients';
		this.removeUrl = this.backendUrl + '/deleteUserIngredients';
	}

	public getUserIngredients(userName: string): Observable<ingredient[]> {
		const params = new HttpParams().append('userName', userName);
		return this.http.get<ingredient[]>(this.userIngredientsUrl, {params});
	}

	public getAll(): Observable<ingredient[]> {
		return this.http.get<ingredient[]>(this.allIngredientsUrl);
	}

	public add(ingredients: ingredient[], userName: string) {
		
		let ingredientsJSON = [];
		ingredientsJSON = [
			{
				"owner": userName,
				"superType": ingredients[0].superType,
				"type": ingredients[0].type,
				"subType": ingredients[0].subType
			}
		]
		for (let i = 1; i < ingredients.length; ++i) {
			ingredientsJSON.push({"owner": userName,
				"superType": ingredients[i].superType,
				"type": ingredients[i].type,
				"subType": ingredients[i].subType
			});
		}

		this.http.post(this.addUrl, ingredientsJSON).subscribe();
	}

	public remove(subType: string[], userName: string) {
		
		///let subTypeString: string = subType.toString();
		const params = new HttpParams().append('subTypes', subType.toString()).append('owner', userName);
		this.http.delete(this.removeUrl, {params}).subscribe();
	}
}





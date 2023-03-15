import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  	providedIn: 'root'
})
export class RecipeService {

	private templateUrl: string;

  	constructor(private http: HttpClient) {
		this.templateUrl = 'http://localhost:8080/templates';
	}
	
	public getNames(): Observable<string[]> {
		//console.log(this.http.get<string>(this.templateUrl));
		return this.http.get<string[]>(this.templateUrl);
	}
}

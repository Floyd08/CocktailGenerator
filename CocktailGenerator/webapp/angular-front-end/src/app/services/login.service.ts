import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
	providedIn: 'root'
})
export class LoginService {

	private backendUrl: string;
	private guestUrl: string;
	private authUrl: string;
	private userName!: string;
	private registerUrl: string;

	constructor(private http: HttpClient) {
		this.backendUrl = environment.apiURL;
		this.guestUrl = this.backendUrl + '/loadGuest';
		this.authUrl = this.backendUrl + '/authenticate';
		this.registerUrl = this.backendUrl + '/register';
	}

	public logInGuest() {
		this.userName = "Guest";
	}

	public logInUser(userName: string, password: string): Observable<number> {
		var userJSON = {"userName": userName, "password": password};
		return this.http.post<number>(this.authUrl, userJSON);
	}

	public registerUser(userName: string, password: string) {
		var userJSON = {"userName": userName, "password": password};
		return this.http.post<number>(this.registerUrl, userJSON);
	}

}

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
	providedIn: 'root'
})
export class LoginService {

	private backendUrl: string;
	private guestUrl: string;
	private userName!: string;

	constructor(private http: HttpClient) {
		this.backendUrl = 'http://localhost:8080';
		
		this.guestUrl = this.backendUrl + '/loadGuest';
	}

	public logInGuest() {
		this.userName = "Guest";
	}
}

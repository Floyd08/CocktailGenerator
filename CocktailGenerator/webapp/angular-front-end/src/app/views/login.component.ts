import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { LoginService } from '../services/login.service';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-page',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginPageComponent implements OnInit {
  
  //private route: ActivatedRoute;
  linkData: Object = "";

  constructor(private route: ActivatedRoute, private AS: AuthenticationService) {}

  ngOnInit() {
    this.linkData = this.route.snapshot.data;
  }

  useAsGuest() {
    this.AS.changeMessage("Guest");
  }

  logInUser(userName: string) {
    this.AS.changeMessage(userName);
  }

  hashPassword(passWord: string) {
    
  }

}

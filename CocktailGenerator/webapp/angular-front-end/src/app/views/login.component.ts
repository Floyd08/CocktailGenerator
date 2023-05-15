import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
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

  constructor(private router: Router, 
              private route: ActivatedRoute, 
              private AS: AuthenticationService, 
              private LS: LoginService) {}

  ngOnInit() {
    this.linkData = this.route.snapshot.data;
  }

  useAsGuest() {
    this.AS.changeMessage("Guest");
  }

  logInUser(userName: string, password: string) {
    this.LS.logInUser(userName, password).subscribe(data => {
        if (data == 1) {
          console.log("user authenticated");
          this.AS.changeMessage(userName);
          this.router.navigate(["../drink-view"], {relativeTo: this.route});
        }
        else {
          console.log("user rejected");
          window.alert("Invalid user credentials");
        }
    })
  }

  registerUser(userName: string, password: string) {
    this.LS.registerUser(userName, password).subscribe(data => {
      if (data == 1) {
        console.log("user created");
        window.alert("New user Registered!");
      }
      else {
        console.log("Registry failed: user exists")
        window.alert("User already exits!");
      }
    })
  }

}

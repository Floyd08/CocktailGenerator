import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-page',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginPageComponent implements OnInit {
  
  //private route: ActivatedRoute;
  linkData: Object = "";

  constructor(private route: ActivatedRoute) {}

  ngOnInit() {
    this.linkData = this.route.snapshot.data;
  }

}

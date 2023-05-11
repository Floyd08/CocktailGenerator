import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginPageComponent } from './views/login.component';
import { DrinkViewComponent } from './drink-view/drink-view.component';
import { InventoryViewComponent } from './views/inventory-view/inventory-view.component';

const routes: Routes = [
	{path: '', redirectTo: '/logIn', pathMatch: 'full'},
	//{path: '', redirectTo: '/drink-view', pathMatch: 'full'},
	{path: 'logIn', redirectTo: '/logIn', pathMatch: 'full'},
	{path: 'drink-view', redirectTo: '/drink-view', pathMatch: 'full'},
	{path: 'inventory-view', redirectTo: '/inventory-view', pathMatch: 'full'},
	{path: 'logIn', component: LoginPageComponent},
	{path: 'drink-view', component: DrinkViewComponent},
	{path: 'inventory-view', component: InventoryViewComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

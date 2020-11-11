import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { SingleTravelWarningComponent } from './single-travel-warning/single-travel-warning.component';
import { UserFormComponent } from './user-form/user-form.component';
import { UserListComponent } from './user-list/user-list.component';


const routes: Routes = [
  // { path: "", pathMatch: "full", redirectTo: "login" },
  { path: "login", component: LoginComponent},
  { path: "savetravel", component: SingleTravelWarningComponent},
  { path: 'users', component: UserListComponent },
  { path: 'adduser', component: UserFormComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

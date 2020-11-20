import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BoardAdminComponent } from './board-admin/board-admin.component';
import { BoardModeratorComponent } from './board-moderator/board-moderator.component';
import { BoardUserComponent } from './board-user/board-user.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { ProfileComponent } from './profile/profile.component';
import { RegisterComponent } from './register/register.component';
import { SingleTravelWarningComponent } from './single-travel-warning/single-travel-warning.component';



const routes: Routes = [
   { path: "savetravel", component: SingleTravelWarningComponent},
   { path: 'home', component: HomeComponent },
   { path: 'login', component: LoginComponent },
   { path: 'register', component: RegisterComponent },
   { path: 'profile', component: ProfileComponent },
   { path: 'user', component: BoardUserComponent },
   { path: 'mod', component: BoardModeratorComponent },
   { path: 'admin', component: BoardAdminComponent },
   { path: '', redirectTo: 'home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes), CommonModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }

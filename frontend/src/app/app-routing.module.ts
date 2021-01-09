import {ModuleWithProviders, NgModule} from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { AuctionsComponent } from './components/auctions/auctions.component';
import { SigninComponent } from './components/signin/signin.component';
import { RegisterComponent } from './components/register/register.component';
import {AuctionsListComponent} from "./components/auctions/auctions-list/auctions-list.component";
import {ProfileComponent} from "./components/profile/profile.component";
import {UpdatePasswordComponent} from "./components/profile/update-password/update-password.component";
import { WonAuctionsListComponent } from './components/auctions/won-auctions-list/won-auctions-list.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    children: [
      { path: 'home', component: HomeComponent },
      { path: 'login', component: SigninComponent },
      { path: 'register', component: RegisterComponent},
      { path: '', component: AuctionsListComponent },
    ]

  },
  {path: 'profile' ,
  component: ProfileComponent,

  children: [
    {path:'password',component: UpdatePasswordComponent}
  ]},
  { path: 'won' ,
  component: WonAuctionsListComponent}

];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routing: ModuleWithProviders<any> = RouterModule.forChild(routes)

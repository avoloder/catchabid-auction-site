import {ModuleWithProviders, NgModule} from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuctionsComponent } from './components/auctions/auctions.component';
import { HomeComponent } from './components/home/home.component';
import { AuthGuard } from './services/auth/auth-guard.service';
import {AuctionsListComponent} from "./components/auctions-list/auctions-list.component";



const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    canActivate: [AuthGuard],
    canActivateChild: [AuthGuard],
    children: [
      { path: '', component: AuctionsListComponent },
    ]
  },
  {path:'auctions',  component: AuctionsListComponent}
];


@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: false})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routing: ModuleWithProviders<any> = RouterModule.forChild(routes)

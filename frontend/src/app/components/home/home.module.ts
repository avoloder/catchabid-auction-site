import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './home.component';
import { RecentAuctionsListComponent } from './recent-auctions-list/recent-auctions-list.component';


@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [HomeComponent, RecentAuctionsListComponent]
})
export class HomeModule { }

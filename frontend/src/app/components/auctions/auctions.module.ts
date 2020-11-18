import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuctionsListComponent } from './auctions-list/auctions-list.component';
import { AuctionsComponent } from './auctions.component';


@NgModule({
  declarations: [AuctionsListComponent, AuctionsComponent],
  imports: [
    CommonModule
  ],
  exports: [

  ]
})
export class AuctionsModule { }

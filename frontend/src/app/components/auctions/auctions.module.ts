import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuctionsComponent } from './auctions.component';
import { AuctionsListComponent } from '../auctions-list/auctions-list.component';


@NgModule({
  declarations: [AuctionsListComponent, AuctionsComponent],
  imports: [
    CommonModule
  ],
  exports: [

  ]
})
export class AuctionsModule { }

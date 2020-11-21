import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuctionsListComponent } from './auctions-list/auctions-list.component';
import { AuctionsComponent } from './auctions.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';


@NgModule({
  declarations: [AuctionsListComponent, AuctionsComponent],
  imports: [
    CommonModule,
    NgbModule,
    FormsModule,
    ReactiveFormsModule
  ],
  exports: [

  ]
})
export class AuctionsModule { }

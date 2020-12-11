import { Component, OnInit, Input } from '@angular/core';
import { AuctionPost } from '../../models/auctionpost';

@Component({
  selector: 'app-auction-details',
  templateUrl: './auction-details.component.html',
  styleUrls: ['./auction-details.component.scss']
})
export class AuctionDetailsComponent implements OnInit {

  @Input() 
  public auction: AuctionPost;

  constructor() { }

  ngOnInit(): void {
    console.log(this.auction)
  }

}

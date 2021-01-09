import { Component, OnInit, Input } from '@angular/core';
import { AuctionsService } from 'src/app/services/auction.service';
import { User } from 'src/app/models/user';
import { AuctionPost } from 'src/app/models/auctionpost';

@Component({
  selector: 'app-won-auctions-list',
  templateUrl: './won-auctions-list.component.html',
  styleUrls: ['./won-auctions-list.component.css']
})
export class WonAuctionsListComponent implements OnInit {

  @Input() user: User;

  wonAuctions: AuctionPost[];

  constructor(
    private auctionService: AuctionsService
  ) { }

  ngOnInit(): void {
    console.log('ok');
    this.getWonAuctions();
  }

  private getWonAuctions() {
    this.auctionService.getWonAuctions().subscribe(
      (res) => this.wonAuctions = res,
      (err) => console.log(err)
    )
  }

}

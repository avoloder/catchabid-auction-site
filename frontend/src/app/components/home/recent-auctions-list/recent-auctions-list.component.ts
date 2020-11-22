import { Component, OnInit } from '@angular/core';
import {AuctionsService} from "../../../services/auctionsService.service";
import {AuctionModel} from "../../../model/auction.model";

@Component({
  selector: 'app-recent-auctions-list',
  templateUrl: './recent-auctions-list.component.html',
  styleUrls: ['./recent-auctions-list.component.scss']
})
export class RecentAuctionsListComponent implements OnInit {

  private pageNumber = 0;
  private pageSize = 20;
  testvar = ["seas", "seas2"];
  auctions : AuctionModel[];

  constructor(private auctionsService: AuctionsService) { }

  ngOnInit(): void {
    this.pullNewAuctions();
  }

  pullNewAuctions(): void {
    this.auctionsService.getRecentPosts(this.pageNumber, this.pageSize)
     .subscribe(result => this.auctions = result); // TODO add to list
    this.pageNumber++;
  }

}

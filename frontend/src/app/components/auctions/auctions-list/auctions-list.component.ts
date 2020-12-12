import {Component, Input, OnInit} from '@angular/core';
import {AuctionsService} from "../../../services/auction.service";
import {AuctionPostModel} from "../../../models/auctionPost.model";


@Component({
  selector: 'app-auctions-list',
  templateUrl: './auctions-list.component.html',
  styleUrls: ['./auctions-list.component.scss']
})
export class AuctionsListComponent implements OnInit {

  constructor(private _dataService: AuctionsService) {
  }

  @Input() auctionsGroup : string;

  private pageNumber: number = 0;
  private pageSize: number = 10;
  noMoreAuctionsToLoad: boolean = false;

  get dataService(): AuctionsService {
    return this._dataService;
  }

  auctions: Array<AuctionPostModel> = [];

  ngOnInit() {
    this.loadMoreAuctions();
  }

  public loadMoreAuctions () {
    const auctionsCountBeforeLoading = this.auctions.length;

    if (this.auctionsGroup == "RECENT") {
      this.dataService.getRecentPosts(this.pageNumber, this.pageSize).subscribe(data => {
        this.auctions = this.auctions.concat(data);
        if(auctionsCountBeforeLoading == this.auctions.length || this.auctions.length < this.pageSize) {
          this.noMoreAuctionsToLoad = true;
        }
      });
    }
    else if (this.auctionsGroup == "UPCOMING") {
      this.dataService.getUpcomingRequests(this.pageNumber, this.pageSize).subscribe(data => {
        this.auctions = this.auctions.concat(data);
        if(auctionsCountBeforeLoading == this.auctions.length || this.auctions.length < this.pageSize)  {
          this.noMoreAuctionsToLoad = true;
        }
      });
    }
    this.pageNumber++;
  }
}

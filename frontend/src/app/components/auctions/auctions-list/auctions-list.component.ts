import {Component, Input, OnInit} from '@angular/core';
import {AuctionsService} from "../../../services/auction.service";
import {AuctionPostModel} from "../../../models/auctionPost.model";
declare var $: any;


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
   
    $(function () {
      $('[data-toggle="tooltip"]').tooltip();
    });
    this.loadMoreAuctions();

  }

  public loadMoreAuctions () {
    const auctionsCountBeforeLoading = this.auctions.length;

    if (this.auctionsGroup == "RECENT") {
      this.dataService.getRecentPosts(this.pageNumber, this.pageSize).subscribe(data => {
        console.log(this.auctionsGroup);
        console.log(data.toString());
        this.auctions = this.auctions.concat(data);
        if(auctionsCountBeforeLoading == this.auctions.length || this.auctions.length < this.pageSize) {
          this.noMoreAuctionsToLoad = true;
        }
      });
    }
    else if (this.auctionsGroup == "UPCOMING") {
      this.dataService.getUpcomingRequests(this.pageNumber, this.pageSize).subscribe(data => {
        console.log(data);
        this.auctions = this.auctions.concat(data);
        if(auctionsCountBeforeLoading == this.auctions.length || this.auctions.length < this.pageSize)  {
          this.noMoreAuctionsToLoad = true;
        }
      });
    }
    this.pageNumber++;
  }
}

import {Component, Input, OnInit} from '@angular/core';
import {DomSanitizer, SafeUrl} from '@angular/platform-browser';
import {AuctionPost} from "../../model/auctionPost.model";
import {AuctionsService} from "../../services/auctionsService.service";


@Component({
  selector: 'app-auctions-list',
  templateUrl: './auctions-list.component.html',
  styleUrls: ['./auctions-list.component.scss']
})
export class AuctionsListComponent implements OnInit {

  constructor(private _dataService: AuctionsService, private _sanitizer: DomSanitizer) {
  }

  @Input() auctionsGroup : string;

  private pageNumber: number = 0;
  private pageSize: number = 9;

  get dataService(): AuctionsService {
    return this._dataService;
  }

  auctions: Array<AuctionPost> = [];

  ngOnInit() {
    this.loadMoreAuctions();
  }

  public loadMoreAuctions () {
    if (this.auctionsGroup == "RECENT") {
      this.dataService.getRecentPosts(this.pageNumber, this.pageSize).subscribe(data => {
        console.log(this.auctionsGroup);
        console.log(data.toString());
        this.auctions = this.auctions.concat(data);
      });
    }
    else if (this.auctionsGroup == "UPCOMING") {
      this.dataService.getUpcomingRequests(this.pageNumber, this.pageSize).subscribe(data => {
        console.log(data);
        this.auctions = this.auctions.concat(data);
      });
    }
    this.pageNumber++;
  }
}

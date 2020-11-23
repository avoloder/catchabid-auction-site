import {ChangeDetectionStrategy, ChangeDetectorRef, Component, HostListener, OnInit} from '@angular/core';
import {AuctionsService} from "../../../services/auctionsService.service";
import {AuctionModel} from "../../../model/auction.model";

@Component({
  selector: 'app-recent-auctions-list',
  templateUrl: './recent-auctions-list.component.html',
  styleUrls: ['./recent-auctions-list.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class RecentAuctionsListComponent implements OnInit {

  private pageNumber = 0;
  private pageSize = 15;
  auctions : AuctionModel[] = [];

  constructor(private auctionsService: AuctionsService, private ref: ChangeDetectorRef) { }

  ngOnInit(): void {
    this.pullNewAuctions();
  }

  pullNewAuctions(): void {

    console.log("CALL pullNewAuctions");
    console.log("page: " + this.pageNumber);
    console.log("size: " + this.pageSize);

    this.auctionsService.getRecentPosts(this.pageNumber, this.pageSize)
       .subscribe(newAuctions => {
         newAuctions.forEach(auction => this.auctions.push(auction));
         this.ref.detectChanges()
       });

    this.pageNumber++;
  }

  // TODO add "scrollToBottomListener" and call pullNewAuctions() angain
}

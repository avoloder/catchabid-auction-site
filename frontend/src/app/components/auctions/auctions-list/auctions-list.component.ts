import {Component, Input, OnInit} from '@angular/core';
import {AuctionsService} from "../../../services/auction.service";
import {AuctionPostModel} from "../../../models/auctionPost.model";
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { BidsComponent } from '../../bids/bids.component';
import { BidsService } from 'src/app/services/bids.service';
import { Subscription } from 'rxjs';


@Component({
  selector: 'app-auctions-list',
  templateUrl: './auctions-list.component.html',
  styleUrls: ['./auctions-list.component.scss']
})
export class AuctionsListComponent implements OnInit {

  modalRef: NgbModalRef;

  bidModalClosedSub: Subscription;

  constructor(
    private _dataService: AuctionsService,
    private bidsService: BidsService,
    private modalService: NgbModal) {
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

  openBidModal(auction: AuctionPostModel): void {
    this.modalRef = this.modalService.open(BidsComponent);
    this.modalRef.componentInstance.auction = auction;

    this.bidModalClosedSub = this.bidsService.bidModalClosed.subscribe(
      () => this.onBidModalClose()
    );
  }

  onBidModalClose(): void {
    this.modalRef.close();
    this.bidModalClosedSub.unsubscribe();
    /**
     * Refresh auctions after placing a bid
     */
    this.pageNumber--; // decrement the pageNumber, so it doesn't load more auctions
    this.auctions = [];
    this.loadMoreAuctions();
  }

}

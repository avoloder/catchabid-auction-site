import {Component, Input, OnInit} from '@angular/core';
import {AuctionsService} from "../../../services/auction.service";
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { SigninComponent } from '../../signin/signin.component';
import { AuctionDetailsComponent } from '../../auction-details/auction-details.component';
import { AuctionPost } from '../../../models/auctionpost';

@Component({
  selector: 'app-auctions-list',
  templateUrl: './auctions-list.component.html',
  styleUrls: ['./auctions-list.component.scss']
})
export class AuctionsListComponent implements OnInit {

  constructor(
    private _dataService: AuctionsService,
    private modalService: NgbModal) {
  }

  @Input() auctionsGroup : string;

  private pageNumber: number = 0;
  private pageSize: number = 10;
  noMoreAuctionsToLoad: boolean = false;

  get dataService(): AuctionsService {
    return this._dataService;
  }

  auctions: Array<AuctionPost> = [];

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

  showAuctionDetails(auction: AuctionPost): void{
    if(localStorage.getItem('token') == null){
      this.openLoginModal();
    }else{
      
      const modal = this.modalService.open(AuctionDetailsComponent,  { size: 'lg', backdrop: 'static' });
      modal.componentInstance.auction = auction;
    }
  }

  subscribeToAuction(): void{
    if(localStorage.getItem('token') == null){
      this.openLoginModal();
    }else{
      console.log("click subscribe");
    }
  }

  openLoginModal(): void {
    this.modalService.open(SigninComponent);
  }
}

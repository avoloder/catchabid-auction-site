import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {AuctionsService} from "../../../services/auction.service";
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { SigninComponent } from '../../signin/signin.component';
import { AuctionDetailsComponent } from '../../auction-details/auction-details.component';
import { AuctionPost } from '../../../models/auctionpost';
import {AuctionSearchQuery} from "../../../models/auctionSearchQuery";
import {LoadingSpinnerService} from "../../../services/loading-spinner.service";
import {AuctionsSearchService} from "../../../services/auctions-search.service";

@Component({
  selector: 'app-auctions-list',
  templateUrl: './auctions-list.component.html',
  styleUrls: ['./auctions-list.component.scss']
})
export class AuctionsListComponent implements OnInit {

  constructor(
    private _dataService: AuctionsService,
    private modalService: NgbModal,
    private _auctionsSearchService: AuctionsSearchService,
    private _loadingSpinnerService: LoadingSpinnerService) {
  }

  @Input() auctionsGroup : string;

  private pageNumber: number = 0;
  private pageSize: number = 10;
  private currentAuctionQuery: AuctionSearchQuery;

  auctions: Array<AuctionPost> = [];
  noMoreAuctionsToLoad: boolean = false;

  @Output() resultSizeEvent = new EventEmitter<number>();

  ngOnInit() {

    this._auctionsSearchService.auctionsQuery.subscribe(query => {

      if(!query.equals(this.currentAuctionQuery)) {

        this._loadingSpinnerService.show();

        this.pageNumber = 0;
        this.auctions = [];
        this.noMoreAuctionsToLoad = false;

        this.currentAuctionQuery = query;
        this.loadMoreAuctions();
        console.log("QUERY:");
        console.log(query.searchKeys);
        console.log(query.categories);
      }
    });
  }

  public loadMoreAuctions () {
    const auctionsCountBeforeLoading = this.auctions.length;

    const handleResponse = data => {
      console.log(this.auctionsGroup);
      console.log(data.toString());
      this.auctions = this.auctions.concat(data);
      if(auctionsCountBeforeLoading == this.auctions.length || (this.auctions.length / (this.pageNumber+1)) < this.pageSize) {
        this.noMoreAuctionsToLoad = true;
      }
      else {
        this.pageNumber++;
      }
      this._loadingSpinnerService.hide();
      this.resultSizeEvent.emit(this.auctions.length);
    };

    let query = new AuctionSearchQuery();
    query.categories = this.currentAuctionQuery.categories;
    query.searchKeys = this.currentAuctionQuery.searchKeys;
    query.countries = this.currentAuctionQuery.countries;
    query.pageNumber = this.pageNumber;
    query.pageSize   = this.pageSize;

    if (this.auctionsGroup == "RECENT") {
      query.sortBy = "endTime";
      query.sortOrder = "DESC";
      query.auctionsStartUntil = new Date();
      query.auctionsEndFrom = new Date();
    }
    if (this.auctionsGroup == "UPCOMING") {
      query.sortBy = "startTime";
      query.sortOrder = "DESC";
      query.auctionsStartFrom = new Date();
    }
    this.dataService.findAuctions(query).subscribe(handleResponse);
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

  get dataService(): AuctionsService {
    return this._dataService;
  }
}

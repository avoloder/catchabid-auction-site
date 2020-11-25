import {BackendAuctionsService} from './backend-auctions.service';
import {Component, OnInit} from '@angular/core';
import {DomSanitizer, SafeUrl} from '@angular/platform-browser';


@Component({
  selector: 'app-auctions-list',
  templateUrl: './auctions-list.component.html',
  styleUrls: ['./auctions-list.component.scss']
})
export class AuctionsListComponent implements OnInit {
  constructor(private _dataService: BackendAuctionsService, private _sanitizer: DomSanitizer) {
  }

  private pageNumber: number = 0;
  private pageSize: number = 9;

  get dataService(): BackendAuctionsService {
    return this._dataService;
  }


  public sanitizerDECODE(decode:string): SafeUrl {
    return this._sanitizer.bypassSecurityTrustUrl(decode);
  }

  products: Array<AuctionPost> = [];

  ngOnInit() {
    this.loadMoreAuctions();
  }

  public loadMoreAuctions () {
    this.dataService.sendGetRequest(this.pageNumber, this.pageSize).subscribe(data => {
      console.log(data);
      this.products = this.products.concat(data);
      this.pageNumber++;
    });
  }
}
export class AuctionPost {


  constructor(private _id: number, private _auctionName: string, private _auctionDescription: string,
              private _creatorId: number, private _creatorName: string, private _status: string,
              private _startTime: Date, private _endTime: Date, private _minPrice: number,
              private _highestBid: number, private _location: { city: string; street: string; houseNr: string; country: string },
              private _image: string, private _sanitizer: DomSanitizer) {}

  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get auctionName(): string {
    return this._auctionName;
  }

  set auctionName(value: string) {
    this._auctionName = value;
  }

  get auctionDescription(): string {
    return this._auctionDescription;
  }

  set auctionDescription(value: string) {
    this._auctionDescription = value;
  }

  get creatorId(): number {
    return this._creatorId;
  }

  set creatorId(value: number) {
    this._creatorId = value;
  }

  get creatorName(): string {
    return this._creatorName;
  }

  set creatorName(value: string) {
    this._creatorName = value;
  }

  get status(): string {
    return this._status;
  }

  set status(value: string) {
    this._status = value;
  }

  get startTime(): Date {
    return this._startTime;
  }

  set startTime(value: Date) {
    this._startTime = value;
  }

  get endTime(): Date {
    return this._endTime;
  }

  set endTime(value: Date) {
    this._endTime = value;
  }

  get minPrice(): number {
    return this._minPrice;
  }

  set minPrice(value: number) {
    this._minPrice = value;
  }

  get highestBid(): number {
    return this._highestBid;
  }

  set highestBid(value: number) {
    this._highestBid = value;
  }

  get location(): { city: string; street: string; houseNr: string; country: string } {
    return this._location;
  }

  set location(value: { city: string; street: string; houseNr: string; country: string }) {
    this._location = value;
  }

  get image(): string {
    return this._image;
  }
  public safeImage(): SafeUrl {
    return this._sanitizer.bypassSecurityTrustResourceUrl(this._image);
  }

  set image(value: string) {
    this._image = value;
  }
}

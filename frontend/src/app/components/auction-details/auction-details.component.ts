import { Component, OnInit, Input } from '@angular/core';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { AuctionPost } from '../../models/auctionpost';
import { AuctionsService } from '../../services/auction.service';
import { BidsComponent } from '../bids/bids.component';
import { BidsService } from 'src/app/services/bids.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-auction-details',
  templateUrl: './auction-details.component.html',
  styleUrls: ['./auction-details.component.scss']
})
export class AuctionDetailsComponent implements OnInit {

  @Input()
  public auction: AuctionPost;

  modalRef: NgbModalRef;

  bidModalClosedSub: Subscription;

  constructor(
    private modalService : NgbModal,
    private auctionService: AuctionsService,
    private bidsService: BidsService) { }

  ngOnInit(): void {
  }


  onModalClose(): void {
    this.modalService.dismissAll();
  }

  openBidModal(auction: AuctionPost): void {
    this.modalRef = this.modalService.open(BidsComponent);
    this.modalRef.componentInstance.auction = auction;

    this.bidModalClosedSub = this.bidsService.bidModalClosed.subscribe(
      () => this.onBidModalClose()
    );
  }

  onBidModalClose(): void {
    this.modalRef.close();
    this.bidModalClosedSub.unsubscribe();
  }

  isAuctionEnded(): boolean {
    const endDate = new Date(this.auction.endTime);
    return endDate.getTime() < new Date().getTime();
  }

}

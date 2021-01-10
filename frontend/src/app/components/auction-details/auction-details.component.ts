import { Component, OnInit, Input } from '@angular/core';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { AuctionPost } from '../../models/auctionpost';
import { AuctionsService } from '../../services/auction.service';
import { BidsComponent } from '../bids/bids.component';
import { BidsService } from 'src/app/services/bids.service';
import { Subscription } from 'rxjs';
import {AuctionCancellationConfirmationComponent} from "./auction-cancellation-confirmation/auction-cancellation-confirmation.component";

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
    this.auctionService.auctionDetailModalClosed.next();
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

  cancelAuction(): void {
    this.modalRef = this.modalService.open(AuctionCancellationConfirmationComponent);

    this.modalRef.result.then((result) => {
      if (result) {
        console.log("cancel auction");
        this.auctionService.cancelAuction(this.auction).subscribe(contactForm => {
          console.log("auction cancelled successfully")
        }, error => {
          console.log("auction cancellation error")
        });
      }
    }
    );
  }

  isOwnAuction(): boolean {
    return this.auction.creatorId.toString() == localStorage.getItem('userId');
  }

  isActive(): boolean {
    return this.auction.status == 'ACTIVE';
  }

  isUpcoming(): boolean {
    return this.auction.status == 'UPCOMING';
  }
}

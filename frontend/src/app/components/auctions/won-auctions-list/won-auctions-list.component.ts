import { Component, OnInit, Input } from '@angular/core';
import { AuctionsService } from 'src/app/services/auction.service';
import { User } from 'src/app/models/user';
import { AuctionPost } from 'src/app/models/auctionpost';
import { NgbModalRef, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { PaymentsComponent } from '../../payments/payments.component';
import { PaymentsService } from 'src/app/services/payments.service';
import { Subscription } from 'rxjs';
import { AuctionDetailsComponent } from '../../auction-details/auction-details.component';

@Component({
  selector: 'app-won-auctions-list',
  templateUrl: './won-auctions-list.component.html',
  styleUrls: ['./won-auctions-list.component.css']
})
export class WonAuctionsListComponent implements OnInit {

  @Input() user: User;

  wonAuctions: AuctionPost[];

  modalRef: NgbModalRef;

  paymentModalClosedSub: Subscription;

  auctionDetailsModalClosedSub: Subscription;

  constructor(
    private auctionService: AuctionsService,
    private modalService: NgbModal,
    private paymentService: PaymentsService
  ) { }

  ngOnInit(): void {
    console.log('ok');
    this.getWonAuctions();
  }

  private getWonAuctions() {
    this.auctionService.getWonAuctions().subscribe(
      (res) => {
        this.wonAuctions = res
      },
      (err) => console.log(err)
    )
  }

  openPaymentModal(auction: AuctionPost): void {
    this.modalRef = this.modalService.open(PaymentsComponent);
    this.modalRef.componentInstance.auction = auction;

    this.paymentModalClosedSub = this.paymentService.paymentModalClosed.subscribe(
      () => this.onPaymentModalClosed()
    );
  }

  onPaymentModalClosed(): void {
    this.modalRef.close();
    this.paymentModalClosedSub.unsubscribe();
    this.getWonAuctions();
  }

  openAuctionDetails(auction: AuctionPost): void{
    const modal = this.modalService.open(AuctionDetailsComponent,  { size: 'lg', backdrop: 'static' });
    modal.componentInstance.auction = auction;
    this.auctionDetailsModalClosedSub = this.paymentService.auctionDetailModalClosed.subscribe(
      () => this.onAuctionDetailsModalClosed()
    )
  }

  onAuctionDetailsModalClosed(): void {
    this.modalRef.close();
    this.auctionDetailsModalClosedSub.unsubscribe();
  }


}

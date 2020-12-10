import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AuctionPostModel } from 'src/app/models/auctionPost.model';
import { BidsService } from 'src/app/services/bids.service';
import { Bid } from 'src/app/models/bid.model';
import { ToastrService } from 'ngx-toastr';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-bids',
  templateUrl: './bids.component.html',
  styleUrls: ['./bids.component.css']
})
export class BidsComponent implements OnInit {

  bidsForm: FormGroup;

  invalidOffer = false;

  newCurrentBidValue: string;

  @Input()
  auction: AuctionPostModel;

  constructor(
    private formBuilder: FormBuilder,
    private bidsService: BidsService,
    private toastr: ToastrService
  ) { }

  ngOnInit(): void {
    this.createForm();
  }

  createForm(): void {
    this.bidsForm = this.formBuilder.group({
      offer: ['', Validators.required]
    });
  }

  onBidSave(): void {
    this.invalidOffer = false;
    if (this.bidsForm.get('offer').value <= this.auction.highestBid) {
      this.invalidOffer = true;
      return;
    }
    const newBid: Bid = {
      offer: this.bidsForm.get('offer').value,
      auctionId: this.auction.id
    };
    this.saveBid(newBid);
  }


  saveBid(bid: Bid): void {
    this.bidsService.saveBid(bid).subscribe(
      () => this.toastr.success('Bid successfully placed'),
      (error) => {
        if (error.status === 409) {
          this.toastr.warning(error.error.message);
          this.newCurrentBidValue = error.error.message.substring(
            error.error.message.lastIndexOf(':') + 1);
          console.log(this.newCurrentBidValue);
        } else {
          this.toastr.error('Error placing bid.');
        }
        console.log(error);
      },
      () => this.onModalClose()
    );
  }

  onModalClose(): void {
    this.bidsService.bidModalClosed.next();
  }
}

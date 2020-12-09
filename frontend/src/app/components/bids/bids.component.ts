import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AuctionPostModel } from 'src/app/models/auctionPost.model';
import { BidsService } from 'src/app/services/bids.service';
import { Bid } from 'src/app/models/bid.model';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-bids',
  templateUrl: './bids.component.html',
  styleUrls: ['./bids.component.css']
})
export class BidsComponent implements OnInit {

  bidsForm: FormGroup;

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
    const newBid: Bid = {
      offer: this.bidsForm.get('offer').value,
      auctionId: this.auction.id
    };
    this.saveBid(newBid);
  }


  saveBid(bid: Bid): void {
    this.bidsService.saveBid(bid).subscribe(
      () => this.toastr.success('Bid successfully placed'),
      (error) => console.log(error),
      () => this.onModalClose()
    );
  }

  onModalClose(): void {
    this.bidsService.bidModalClosed.next();
  }
}

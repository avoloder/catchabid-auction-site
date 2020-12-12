import { Component, OnInit, Input } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AuctionPost } from '../../models/auctionpost';
import { AuctionsService } from '../../services/auction.service';

@Component({
  selector: 'app-auction-details',
  templateUrl: './auction-details.component.html',
  styleUrls: ['./auction-details.component.scss']
})
export class AuctionDetailsComponent implements OnInit {

  @Input() 
  public auction: AuctionPost;

  constructor(private modalService : NgbModal) { }

  ngOnInit(): void {
  }

  onModalClose(): void {
    this.modalService.dismissAll();
  }

}

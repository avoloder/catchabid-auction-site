import { Component, OnInit, Input } from '@angular/core';
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

  constructor(private auctionsService: AuctionsService) { }

  ngOnInit(): void {
  }

  onModalClose(): void {
    this.auctionsService.auctionDetailModalClosed.next();
  }

}

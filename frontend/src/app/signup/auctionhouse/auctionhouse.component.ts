import { Component, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-auctionhouse',
  templateUrl: './auctionhouse.component.html',
  styleUrls: ['./auctionhouse.component.css']
})
export class AuctionhouseComponent implements OnInit {

  constructor(public activeModal: NgbActiveModal) { }

  ngOnInit(): void {
  }

}

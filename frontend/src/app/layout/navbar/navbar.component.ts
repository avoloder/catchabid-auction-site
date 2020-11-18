import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AuctionsComponent } from '../../auctions/auctions.component';
import { AuctionhouseComponent } from '../../signup/auctionhouse/auctionhouse.component';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  public focus;

  signedIn = false;

  constructor(private modalService: NgbModal) { }

  ngOnInit(): void {
  }

  changeLogin(): void {
    this.signedIn = !this.signedIn;
  }

  openModal() {
    //Here you define the name of your component
    this.modalService.open(AuctionhouseComponent);
    //This section is if you want to have any variable to initialize
    //compConst.componentInstance.weight = undefined;
}

}

import { Component, OnInit } from '@angular/core';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { SigninComponent } from '../../signin/signin.component';
import { RegisterComponent } from '../../register/register.component';
import { Subscription } from 'rxjs';
import { AuctionFormComponent } from '../../auctions/auction-form/auction-form.component';
import { Router } from '@angular/router';
import {AuctionsService} from '../../../services/auction.service';
import {ContactFormComponent} from "../../auctions/contact-form/contact-form.component";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  public focus;
  public activeModal;

  closeResult = '';

  auctionClosedSub: Subscription;

  modalRef: NgbModalRef;

  userName: any;

  constructor(
    private modalService: NgbModal,
    private auctionsService: AuctionsService,
    private router: Router) { }

  ngOnInit(): void {
    console.log("on init");
  }

  openLoginModal(): void {
    this.modalService.open(SigninComponent);
  }

  openRegisterModal(): void {
    this.modalService.open(RegisterComponent);
  }


  createAuction(): void {
    this.modalRef = this.modalService.open(AuctionFormComponent, { size: 'lg', backdrop: 'static' });
    this.auctionClosedSub = this.auctionsService.auctionFormModalClosed.subscribe(
      () => this.onAuctionFormClose()
    );
  }

  onAuctionFormClose(): void {
    this.modalRef.close();
    this.auctionClosedSub.unsubscribe();
  }

  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('userName');
    this.userName = null;
  }

  isUserLoggedIn(){
    if(localStorage.getItem('token') !== null){
      this.userName = localStorage.getItem('userName');
      return true;
    }else{
      return false;
    }
  }

}

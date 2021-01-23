import { Component, OnInit } from '@angular/core';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { SigninComponent } from '../../signin/signin.component';
import { RegisterComponent } from '../../register/register.component';
import { Subscription } from 'rxjs';
import { AuctionFormComponent } from '../../auctions/auction-form/auction-form.component';
import { Router } from '@angular/router';
import {AuctionsService} from '../../../services/auction.service';
import {AuctionsSearchService} from "../../../services/auctions-search.service";
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

  searchInputText: string;

  constructor(
    private modalService: NgbModal,
    private auctionsService: AuctionsService,
    private router: Router,
    private auctionsSearchService: AuctionsSearchService
  ) { }

  ngOnInit(): void {
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
    localStorage.removeItem('email');
    this.userName = null;
    this.router.navigate(['']);
  }

  toUserSettings(){
    this.router.navigate(['/profile']);
  }
  toAuctionStatiststics(){
    this.router.navigate(['/statistics']);
  }

  isUserLoggedIn(){
    if(localStorage.getItem('token') !== null){
      this.userName = localStorage.getItem('userName');
      return true;
    }else{
      return false;
    }
  }

  navigateHome(){
    this.router.navigate(['']);
  }

  processTextInput($event) {
    console.log("processing search query text");
    const searchKeys = this.searchInputText.toString().split(" ");
    this.auctionsSearchService.updateSearchKeys(searchKeys);

    // prevent default behaviour and do not "submit" form
    return false;
  }

  clearTestInput() {
    this.searchInputText = "";
    this.auctionsSearchService.updateSearchKeys([]);
  }
}

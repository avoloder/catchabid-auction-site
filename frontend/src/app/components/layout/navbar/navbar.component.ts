import { Component, OnInit } from '@angular/core';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { SigninComponent } from '../../signin/signin.component';
import { RegisterComponent } from '../../register/register.component';
import { Subscription } from 'rxjs';
import { AuctionFormComponent } from '../../auctions/auction-form/auction-form.component';
import { AuctionsService } from '../../auctions.service';
import { Router } from '@angular/router';
import jwt_decode from 'jwt-decode';
import { UserService } from '../../../services/user.service';
import { User } from '../../../models/user';
import { AuctionHouse } from '../../../models/auctionhouse';

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

  user: User|AuctionHouse;

  constructor(
    private modalService: NgbModal,
    private auctionsService: AuctionsService,
    private userService: UserService,
    private router: Router) { }

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
    this.router.navigate(['/home']);
  }

  isUserLoggedIn(){
    if(localStorage.getItem('token') !== null){
/*       let email = jwt_decode(localStorage.getItem('token'))['sub'];
      let userJSON = this.userService.findByEmail(email);
      console.log(userJSON) */
      return true;
    }else{
      return false;
    }
  }

}

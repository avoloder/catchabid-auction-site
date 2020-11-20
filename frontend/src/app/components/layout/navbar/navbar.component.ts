import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { SigninComponent } from '../../signin/signin.component';
import { RegisterComponent } from '../../register/register.component';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  public focus;
  public activeModal

  signedIn = false;

  constructor(private modalService: NgbModal) { }

  ngOnInit(): void {
  }

  changeLogin(): void {
    this.signedIn = !this.signedIn;
  }

  openLoginModal() {   
    this.modalService.open(SigninComponent);
  }

  openRegisterModal(){
    this.modalService.open(RegisterComponent)
  }

}

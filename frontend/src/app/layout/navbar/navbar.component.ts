import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
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

  signedIn = false;

  constructor(private modalService: NgbModal) { }

  ngOnInit(): void {
  }

  changeLogin(): void {
    this.signedIn = !this.signedIn;
  }

  openLoginModal() {
    //Here you define the name of your component
    this.modalService.open(SigninComponent);
    //This section is if you want to have any variable to initialize
    //compConst.componentInstance.weight = undefined;
  }

  openRegisterModal(){
    this.modalService.open(RegisterComponent)
  }

}

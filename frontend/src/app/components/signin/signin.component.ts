import { Component, OnInit, Output } from '@angular/core';
import { RegisterComponent } from '../register/register.component';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { EventEmitter } from '@angular/core';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.scss']
})
export class SigninComponent implements OnInit {

  constructor(private modalService: NgbModal) { }

  openRegisterModal(){
    this.modalService.dismissAll();
    this.modalService.open(RegisterComponent)
  }

  ngOnInit(): void {
  }

}

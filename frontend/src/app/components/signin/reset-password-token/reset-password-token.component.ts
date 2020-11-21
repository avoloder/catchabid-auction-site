import { Component, OnInit } from '@angular/core';
import { ResetPasswordComponent } from '../reset-password/reset-password.component';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { PasswordManagementService } from '../../../services/password-management.service';

@Component({
  selector: 'app-reset-password-token',
  templateUrl: './reset-password-token.component.html',
  styleUrls: ['./reset-password-token.component.scss']
})
export class ResetPasswordTokenComponent implements OnInit {

  token: Number;

  constructor(
    private modalService: NgbModal,
    private passwordManagementService: PasswordManagementService
  ) { }

  ngOnInit(): void {
  }

  sendResetPasswordToken(){
    this.passwordManagementService.sendResetPasswordToken(this.token)
    .subscribe(
      data => {
      },
      error => {
        console.log(error);
    });
    
  }

  openResetPasswordModal(){
    this.sendResetPasswordToken()
    this.modalService.dismissAll();
    this.modalService.open(ResetPasswordComponent);
  }


}

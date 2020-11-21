import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ResetPasswordTokenComponent } from '../reset-password-token/reset-password-token.component';
import { PasswordManagementService } from '../../../services/password-management.service';

@Component({
  selector: 'app-reset-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.scss']
})
export class ForgotPasswordComponent implements OnInit {

  email: String;

  constructor(
    private modalService: NgbModal,
    private passwordManagementService: PasswordManagementService
  ) { }

  ngOnInit(): void {
  }

  requestPasswordReset(){
    this.passwordManagementService.requestPasswordReset(this.email)
    .subscribe(
      data => {
      },
      error => {
        console.log(error);
      });
  }

  openResetPasswordTokenModal(){
    this.requestPasswordReset();
    this.modalService.dismissAll();
    this.modalService.open(ResetPasswordTokenComponent);
  }

}

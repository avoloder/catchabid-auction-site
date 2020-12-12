import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FooterComponent } from './footer/footer.component';
import { NavbarComponent } from './navbar/navbar.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ImageCropperModule } from 'ngx-image-cropper';



@NgModule({
  declarations: [
    FooterComponent,
    NavbarComponent],
  imports: [
    CommonModule,
    NgbModule,
    ImageCropperModule
  ],
  exports: [
    NavbarComponent,
    FooterComponent
  ]
})
export class LayoutModule { }

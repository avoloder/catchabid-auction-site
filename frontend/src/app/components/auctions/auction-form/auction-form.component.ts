import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {AuctionPostModel} from '../../../models/auctionPost.model';
import {ToastrService} from 'ngx-toastr';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {AuctionsService} from "../../../services/auctionsService.service";

@Component({
  selector: 'app-auction-form',
  templateUrl: './auction-form.component.html',
  styleUrls: ['./auction-form.component.scss']
})
export class AuctionFormComponent implements OnInit {

  image = 'Choose file';
  imgPreview: any;
  imageFile: string;
  nameForm = new FormControl('', [Validators.required]);
  categoryForm = new FormControl('', [Validators.required]);
  startDateForm = new FormControl('', [Validators.required]);
  startTimeForm = new FormControl('', [Validators.required]);
  endDateForm = new FormControl('', [Validators.required]);
  endTimeForm = new FormControl('', [Validators.required, Validators.min(0)]);
  locationForm = new FormControl();
  priceForm = new FormControl('', [Validators.required, Validators.min(0)]);
  descriptionForm = new FormControl();
  imageForm = new FormControl('', [Validators.required]);
  form = new FormGroup({
    name: this.nameForm,
    category: this.categoryForm,
    startDate: this.startDateForm,
    startTime: this.startTimeForm,
    endDate: this.endDateForm,
    endTime: this.endTimeForm,
    location: this.locationForm,
    minPrice: this.priceForm,
    description: this.descriptionForm,
    image: this.imageForm
  });
  categories = ['jewelry', 'electronics', 'cars', 'experience', 'travel', 'furniture', 'music', 'other'];

  constructor(private auctionsService: AuctionsService, private toast: ToastrService, private modalService: NgbModal) { }

  ngOnInit(): void {
    console.log('opened');
    this.imgPreview = 'assets/img/placeholder-image-300x225.png';
    // this.resetForm();
  }

  inputImage($event: Event): void {
    const file = ($event.target as HTMLInputElement).files[0];
    this.getBase64(file);
    this.image = file.name;
  }

  getErrorMessage(): string {
    return 'You must enter a value';
  }

  resetForm(): void {
    this.form.reset();
    this.nameForm.setErrors(null);
    // for (let conts in this.form.controls) {
    //   this.form.get(conts).setErrors(null);
    // }
  }

  onModalClose(): void {
    this.auctionsService.auctionFormModalClosed.next();
  }

  getBase64(file: File): void {
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = (file64: any) => {
      this.imageFile = file64.target.result.split(',')[1];
      this.imgPreview = 'data:image/png;base64,' + this.imageFile;
    };
  }

  saveAuction(): void{
    const startDate = new Date(this.startDateForm.value.year, this.startDateForm.value.month,
      this.startDateForm.value.day, this.startTimeForm.value.hour, this.startTimeForm.value.minute);

    const endDate = new Date(this.endDateForm.value.year, this.endDateForm.value.month,
      this.endDateForm.value.day, this.endTimeForm.value.hour, this.endTimeForm.value.minute);

    const auctionPost = new AuctionPostModel(null, this.nameForm.value, this.categoryForm.value.toUpperCase(),
      new Date(startDate), new Date(endDate), this.priceForm.value, this.descriptionForm.value, this.imageFile);

    console.log(auctionPost);
    this.auctionsService.saveAuction(auctionPost).subscribe(post => {
      console.log(post);
      this.toast.success('Auction successfully saved');
      this.onModalClose();
    }, error => {
      this.toast.error('Failed to save the auction');
    });
    console.log(auctionPost);
  }

}

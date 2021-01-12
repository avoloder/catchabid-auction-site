import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WonAuctionsListComponent } from './won-auctions-list.component';

describe('WonAuctionsListComponent', () => {
  let component: WonAuctionsListComponent;
  let fixture: ComponentFixture<WonAuctionsListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WonAuctionsListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WonAuctionsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

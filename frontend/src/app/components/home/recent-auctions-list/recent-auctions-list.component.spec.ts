import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecentAuctionsListComponent } from './recent-auctions-list.component';

describe('RecentAuctionsListComponent', () => {
  let component: RecentAuctionsListComponent;
  let fixture: ComponentFixture<RecentAuctionsListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RecentAuctionsListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RecentAuctionsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

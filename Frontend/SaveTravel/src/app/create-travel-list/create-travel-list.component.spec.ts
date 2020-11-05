import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateTravelListComponent } from './create-travel-list.component';

describe('CreateTravelListComponent', () => {
  let component: CreateTravelListComponent;
  let fixture: ComponentFixture<CreateTravelListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateTravelListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateTravelListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

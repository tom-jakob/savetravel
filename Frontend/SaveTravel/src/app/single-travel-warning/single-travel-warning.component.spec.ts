import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SingleTravelWarningComponent } from './single-travel-warning.component';

describe('SingleTravelWarningComponent', () => {
  let component: SingleTravelWarningComponent;
  let fixture: ComponentFixture<SingleTravelWarningComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SingleTravelWarningComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SingleTravelWarningComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

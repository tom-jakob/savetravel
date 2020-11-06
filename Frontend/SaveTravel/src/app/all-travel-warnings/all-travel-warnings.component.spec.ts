import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AllTravelWarningsComponent } from './all-travel-warnings.component';

describe('AllTravelWarningsComponent', () => {
  let component: AllTravelWarningsComponent;
  let fixture: ComponentFixture<AllTravelWarningsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AllTravelWarningsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AllTravelWarningsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssignmentsProgettoComponent } from './assignments-progetto.component';

describe('AssignmentsProgettoComponent', () => {
  let component: AssignmentsProgettoComponent;
  let fixture: ComponentFixture<AssignmentsProgettoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AssignmentsProgettoComponent]
    });
    fixture = TestBed.createComponent(AssignmentsProgettoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssignProgUserComponent } from './assign-prog-user.component';

describe('AssignProgUserComponent', () => {
  let component: AssignProgUserComponent;
  let fixture: ComponentFixture<AssignProgUserComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AssignProgUserComponent]
    });
    fixture = TestBed.createComponent(AssignProgUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

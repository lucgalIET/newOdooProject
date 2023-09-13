import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssignProgActivityComponent } from './assign-prog-activity.component';

describe('AssignProgActivityComponent', () => {
  let component: AssignProgActivityComponent;
  let fixture: ComponentFixture<AssignProgActivityComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AssignProgActivityComponent]
    });
    fixture = TestBed.createComponent(AssignProgActivityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

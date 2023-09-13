import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssignProgMessageComponent } from './assign-prog-message.component';

describe('AssignProgMessageComponent', () => {
  let component: AssignProgMessageComponent;
  let fixture: ComponentFixture<AssignProgMessageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AssignProgMessageComponent]
    });
    fixture = TestBed.createComponent(AssignProgMessageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssignProgNoteComponent } from './assign-prog-note.component';

describe('AssignProgNoteComponent', () => {
  let component: AssignProgNoteComponent;
  let fixture: ComponentFixture<AssignProgNoteComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AssignProgNoteComponent]
    });
    fixture = TestBed.createComponent(AssignProgNoteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

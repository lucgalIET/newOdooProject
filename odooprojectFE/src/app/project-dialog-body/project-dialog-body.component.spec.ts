import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjectDialogBodyComponent } from './project-dialog-body.component';

describe('ProjectDialogBodyComponent', () => {
  let component: ProjectDialogBodyComponent;
  let fixture: ComponentFixture<ProjectDialogBodyComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ProjectDialogBodyComponent]
    });
    fixture = TestBed.createComponent(ProjectDialogBodyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

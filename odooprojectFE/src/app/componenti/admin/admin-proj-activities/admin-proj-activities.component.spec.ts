import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminProjActivitiesComponent } from './admin-proj-activities.component';

describe('AdminProjActivitiesComponent', () => {
  let component: AdminProjActivitiesComponent;
  let fixture: ComponentFixture<AdminProjActivitiesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AdminProjActivitiesComponent]
    });
    fixture = TestBed.createComponent(AdminProjActivitiesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

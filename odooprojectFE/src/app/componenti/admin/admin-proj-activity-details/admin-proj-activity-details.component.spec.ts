import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminProjActivityDetailsComponent } from './admin-proj-activity-details.component';

describe('AdminProjActivityDetailsComponent', () => {
  let component: AdminProjActivityDetailsComponent;
  let fixture: ComponentFixture<AdminProjActivityDetailsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AdminProjActivityDetailsComponent]
    });
    fixture = TestBed.createComponent(AdminProjActivityDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminProjectCreateComponent } from './admin-project-create.component';

describe('AdminProjectCreateComponent', () => {
  let component: AdminProjectCreateComponent;
  let fixture: ComponentFixture<AdminProjectCreateComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AdminProjectCreateComponent]
    });
    fixture = TestBed.createComponent(AdminProjectCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

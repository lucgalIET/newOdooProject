import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MieiProgettiComponent } from './miei-progetti.component';

describe('MieiProgettiComponent', () => {
  let component: MieiProgettiComponent;
  let fixture: ComponentFixture<MieiProgettiComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MieiProgettiComponent]
    });
    fixture = TestBed.createComponent(MieiProgettiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

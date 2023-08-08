import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MieAttivitaComponent } from './mie-attivita.component';

describe('MieAttivitaComponent', () => {
  let component: MieAttivitaComponent;
  let fixture: ComponentFixture<MieAttivitaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MieAttivitaComponent]
    });
    fixture = TestBed.createComponent(MieAttivitaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

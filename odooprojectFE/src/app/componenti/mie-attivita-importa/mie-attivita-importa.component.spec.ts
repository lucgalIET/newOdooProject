import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MieAttivitaImportaComponent } from './mie-attivita-importa.component';

describe('MieAttivitaImportaComponent', () => {
  let component: MieAttivitaImportaComponent;
  let fixture: ComponentFixture<MieAttivitaImportaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MieAttivitaImportaComponent]
    });
    fixture = TestBed.createComponent(MieAttivitaImportaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

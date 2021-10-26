import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MovementBankDetailComponent } from './movement-bank-detail.component';

describe('Component Tests', () => {
  describe('MovementBank Management Detail Component', () => {
    let comp: MovementBankDetailComponent;
    let fixture: ComponentFixture<MovementBankDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [MovementBankDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ movementBank: { id: 123 } }) },
          },
        ],
      })
        .overrideTemplate(MovementBankDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MovementBankDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load movementBank on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.movementBank).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
  });
});

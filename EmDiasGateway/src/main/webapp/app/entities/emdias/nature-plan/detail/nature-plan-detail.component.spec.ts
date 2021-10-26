import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { NaturePlanDetailComponent } from './nature-plan-detail.component';

describe('Component Tests', () => {
  describe('NaturePlan Management Detail Component', () => {
    let comp: NaturePlanDetailComponent;
    let fixture: ComponentFixture<NaturePlanDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [NaturePlanDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ naturePlan: { id: 123 } }) },
          },
        ],
      })
        .overrideTemplate(NaturePlanDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(NaturePlanDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load naturePlan on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.naturePlan).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
  });
});

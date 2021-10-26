import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { NaturePlanService } from '../service/nature-plan.service';

import { NaturePlanComponent } from './nature-plan.component';

describe('Component Tests', () => {
  describe('NaturePlan Management Component', () => {
    let comp: NaturePlanComponent;
    let fixture: ComponentFixture<NaturePlanComponent>;
    let service: NaturePlanService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [NaturePlanComponent],
      })
        .overrideTemplate(NaturePlanComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(NaturePlanComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(NaturePlanService);

      const headers = new HttpHeaders().append('link', 'link;link');
      jest.spyOn(service, 'query').mockReturnValue(
        of(
          new HttpResponse({
            body: [{ id: 123 }],
            headers,
          })
        )
      );
    });

    it('Should call load all on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.naturePlans?.[0]).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});

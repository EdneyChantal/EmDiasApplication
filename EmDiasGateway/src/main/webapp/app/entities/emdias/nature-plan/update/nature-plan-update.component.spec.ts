jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { NaturePlanService } from '../service/nature-plan.service';
import { INaturePlan, NaturePlan } from '../nature-plan.model';
import { IWorkSpace } from 'app/entities/emdias/work-space/work-space.model';
import { WorkSpaceService } from 'app/entities/emdias/work-space/service/work-space.service';

import { NaturePlanUpdateComponent } from './nature-plan-update.component';

describe('Component Tests', () => {
  describe('NaturePlan Management Update Component', () => {
    let comp: NaturePlanUpdateComponent;
    let fixture: ComponentFixture<NaturePlanUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let naturePlanService: NaturePlanService;
    let workSpaceService: WorkSpaceService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [NaturePlanUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(NaturePlanUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(NaturePlanUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      naturePlanService = TestBed.inject(NaturePlanService);
      workSpaceService = TestBed.inject(WorkSpaceService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should call WorkSpace query and add missing value', () => {
        const naturePlan: INaturePlan = { id: 456 };
        const workSpace: IWorkSpace = { id: 6098 };
        naturePlan.workSpace = workSpace;

        const workSpaceCollection: IWorkSpace[] = [{ id: 26291 }];
        jest.spyOn(workSpaceService, 'query').mockReturnValue(of(new HttpResponse({ body: workSpaceCollection })));
        const additionalWorkSpaces = [workSpace];
        const expectedCollection: IWorkSpace[] = [...additionalWorkSpaces, ...workSpaceCollection];
        jest.spyOn(workSpaceService, 'addWorkSpaceToCollectionIfMissing').mockReturnValue(expectedCollection);

        activatedRoute.data = of({ naturePlan });
        comp.ngOnInit();

        expect(workSpaceService.query).toHaveBeenCalled();
        expect(workSpaceService.addWorkSpaceToCollectionIfMissing).toHaveBeenCalledWith(workSpaceCollection, ...additionalWorkSpaces);
        expect(comp.workSpacesSharedCollection).toEqual(expectedCollection);
      });

      it('Should update editForm', () => {
        const naturePlan: INaturePlan = { id: 456 };
        const workSpace: IWorkSpace = { id: 57654 };
        naturePlan.workSpace = workSpace;

        activatedRoute.data = of({ naturePlan });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(naturePlan));
        expect(comp.workSpacesSharedCollection).toContain(workSpace);
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<NaturePlan>>();
        const naturePlan = { id: 123 };
        jest.spyOn(naturePlanService, 'update').mockReturnValue(saveSubject);
        jest.spyOn(comp, 'previousState');
        activatedRoute.data = of({ naturePlan });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: naturePlan }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(naturePlanService.update).toHaveBeenCalledWith(naturePlan);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<NaturePlan>>();
        const naturePlan = new NaturePlan();
        jest.spyOn(naturePlanService, 'create').mockReturnValue(saveSubject);
        jest.spyOn(comp, 'previousState');
        activatedRoute.data = of({ naturePlan });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: naturePlan }));
        saveSubject.complete();

        // THEN
        expect(naturePlanService.create).toHaveBeenCalledWith(naturePlan);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<NaturePlan>>();
        const naturePlan = { id: 123 };
        jest.spyOn(naturePlanService, 'update').mockReturnValue(saveSubject);
        jest.spyOn(comp, 'previousState');
        activatedRoute.data = of({ naturePlan });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(naturePlanService.update).toHaveBeenCalledWith(naturePlan);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });

    describe('Tracking relationships identifiers', () => {
      describe('trackWorkSpaceById', () => {
        it('Should return tracked WorkSpace primary key', () => {
          const entity = { id: 123 };
          const trackResult = comp.trackWorkSpaceById(0, entity);
          expect(trackResult).toEqual(entity.id);
        });
      });
    });
  });
});

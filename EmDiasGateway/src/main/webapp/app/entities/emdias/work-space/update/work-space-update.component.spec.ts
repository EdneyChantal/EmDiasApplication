jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { WorkSpaceService } from '../service/work-space.service';
import { IWorkSpace, WorkSpace } from '../work-space.model';

import { WorkSpaceUpdateComponent } from './work-space-update.component';

describe('Component Tests', () => {
  describe('WorkSpace Management Update Component', () => {
    let comp: WorkSpaceUpdateComponent;
    let fixture: ComponentFixture<WorkSpaceUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let workSpaceService: WorkSpaceService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [WorkSpaceUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(WorkSpaceUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WorkSpaceUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      workSpaceService = TestBed.inject(WorkSpaceService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should update editForm', () => {
        const workSpace: IWorkSpace = { id: 456 };

        activatedRoute.data = of({ workSpace });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(workSpace));
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<WorkSpace>>();
        const workSpace = { id: 123 };
        jest.spyOn(workSpaceService, 'update').mockReturnValue(saveSubject);
        jest.spyOn(comp, 'previousState');
        activatedRoute.data = of({ workSpace });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: workSpace }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(workSpaceService.update).toHaveBeenCalledWith(workSpace);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<WorkSpace>>();
        const workSpace = new WorkSpace();
        jest.spyOn(workSpaceService, 'create').mockReturnValue(saveSubject);
        jest.spyOn(comp, 'previousState');
        activatedRoute.data = of({ workSpace });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: workSpace }));
        saveSubject.complete();

        // THEN
        expect(workSpaceService.create).toHaveBeenCalledWith(workSpace);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<WorkSpace>>();
        const workSpace = { id: 123 };
        jest.spyOn(workSpaceService, 'update').mockReturnValue(saveSubject);
        jest.spyOn(comp, 'previousState');
        activatedRoute.data = of({ workSpace });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(workSpaceService.update).toHaveBeenCalledWith(workSpace);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});

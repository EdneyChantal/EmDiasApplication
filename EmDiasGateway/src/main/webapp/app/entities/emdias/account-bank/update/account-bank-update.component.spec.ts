jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { AccountBankService } from '../service/account-bank.service';
import { IAccountBank, AccountBank } from '../account-bank.model';
import { IWorkSpace } from 'app/entities/emdias/work-space/work-space.model';
import { WorkSpaceService } from 'app/entities/emdias/work-space/service/work-space.service';

import { AccountBankUpdateComponent } from './account-bank-update.component';

describe('Component Tests', () => {
  describe('AccountBank Management Update Component', () => {
    let comp: AccountBankUpdateComponent;
    let fixture: ComponentFixture<AccountBankUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let accountBankService: AccountBankService;
    let workSpaceService: WorkSpaceService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [AccountBankUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(AccountBankUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AccountBankUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      accountBankService = TestBed.inject(AccountBankService);
      workSpaceService = TestBed.inject(WorkSpaceService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should call WorkSpace query and add missing value', () => {
        const accountBank: IAccountBank = { id: 456 };
        const workSpace: IWorkSpace = { id: 65952 };
        accountBank.workSpace = workSpace;

        const workSpaceCollection: IWorkSpace[] = [{ id: 201 }];
        jest.spyOn(workSpaceService, 'query').mockReturnValue(of(new HttpResponse({ body: workSpaceCollection })));
        const additionalWorkSpaces = [workSpace];
        const expectedCollection: IWorkSpace[] = [...additionalWorkSpaces, ...workSpaceCollection];
        jest.spyOn(workSpaceService, 'addWorkSpaceToCollectionIfMissing').mockReturnValue(expectedCollection);

        activatedRoute.data = of({ accountBank });
        comp.ngOnInit();

        expect(workSpaceService.query).toHaveBeenCalled();
        expect(workSpaceService.addWorkSpaceToCollectionIfMissing).toHaveBeenCalledWith(workSpaceCollection, ...additionalWorkSpaces);
        expect(comp.workSpacesSharedCollection).toEqual(expectedCollection);
      });

      it('Should update editForm', () => {
        const accountBank: IAccountBank = { id: 456 };
        const workSpace: IWorkSpace = { id: 52105 };
        accountBank.workSpace = workSpace;

        activatedRoute.data = of({ accountBank });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(accountBank));
        expect(comp.workSpacesSharedCollection).toContain(workSpace);
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<AccountBank>>();
        const accountBank = { id: 123 };
        jest.spyOn(accountBankService, 'update').mockReturnValue(saveSubject);
        jest.spyOn(comp, 'previousState');
        activatedRoute.data = of({ accountBank });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: accountBank }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(accountBankService.update).toHaveBeenCalledWith(accountBank);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<AccountBank>>();
        const accountBank = new AccountBank();
        jest.spyOn(accountBankService, 'create').mockReturnValue(saveSubject);
        jest.spyOn(comp, 'previousState');
        activatedRoute.data = of({ accountBank });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: accountBank }));
        saveSubject.complete();

        // THEN
        expect(accountBankService.create).toHaveBeenCalledWith(accountBank);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<AccountBank>>();
        const accountBank = { id: 123 };
        jest.spyOn(accountBankService, 'update').mockReturnValue(saveSubject);
        jest.spyOn(comp, 'previousState');
        activatedRoute.data = of({ accountBank });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(accountBankService.update).toHaveBeenCalledWith(accountBank);
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

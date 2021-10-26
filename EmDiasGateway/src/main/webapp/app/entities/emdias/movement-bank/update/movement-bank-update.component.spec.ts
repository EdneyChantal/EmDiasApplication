jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { MovementBankService } from '../service/movement-bank.service';
import { IMovementBank, MovementBank } from '../movement-bank.model';
import { IAccountBank } from 'app/entities/emdias/account-bank/account-bank.model';
import { AccountBankService } from 'app/entities/emdias/account-bank/service/account-bank.service';
import { INaturePlan } from 'app/entities/emdias/nature-plan/nature-plan.model';
import { NaturePlanService } from 'app/entities/emdias/nature-plan/service/nature-plan.service';

import { MovementBankUpdateComponent } from './movement-bank-update.component';

describe('Component Tests', () => {
  describe('MovementBank Management Update Component', () => {
    let comp: MovementBankUpdateComponent;
    let fixture: ComponentFixture<MovementBankUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let movementBankService: MovementBankService;
    let accountBankService: AccountBankService;
    let naturePlanService: NaturePlanService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [MovementBankUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(MovementBankUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MovementBankUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      movementBankService = TestBed.inject(MovementBankService);
      accountBankService = TestBed.inject(AccountBankService);
      naturePlanService = TestBed.inject(NaturePlanService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should call AccountBank query and add missing value', () => {
        const movementBank: IMovementBank = { id: 456 };
        const accountBank: IAccountBank = { id: 68879 };
        movementBank.accountBank = accountBank;

        const accountBankCollection: IAccountBank[] = [{ id: 94581 }];
        jest.spyOn(accountBankService, 'query').mockReturnValue(of(new HttpResponse({ body: accountBankCollection })));
        const additionalAccountBanks = [accountBank];
        const expectedCollection: IAccountBank[] = [...additionalAccountBanks, ...accountBankCollection];
        jest.spyOn(accountBankService, 'addAccountBankToCollectionIfMissing').mockReturnValue(expectedCollection);

        activatedRoute.data = of({ movementBank });
        comp.ngOnInit();

        expect(accountBankService.query).toHaveBeenCalled();
        expect(accountBankService.addAccountBankToCollectionIfMissing).toHaveBeenCalledWith(
          accountBankCollection,
          ...additionalAccountBanks
        );
        expect(comp.accountBanksSharedCollection).toEqual(expectedCollection);
      });

      it('Should call NaturePlan query and add missing value', () => {
        const movementBank: IMovementBank = { id: 456 };
        const naturePlan: INaturePlan = { id: 2960 };
        movementBank.naturePlan = naturePlan;

        const naturePlanCollection: INaturePlan[] = [{ id: 85796 }];
        jest.spyOn(naturePlanService, 'query').mockReturnValue(of(new HttpResponse({ body: naturePlanCollection })));
        const additionalNaturePlans = [naturePlan];
        const expectedCollection: INaturePlan[] = [...additionalNaturePlans, ...naturePlanCollection];
        jest.spyOn(naturePlanService, 'addNaturePlanToCollectionIfMissing').mockReturnValue(expectedCollection);

        activatedRoute.data = of({ movementBank });
        comp.ngOnInit();

        expect(naturePlanService.query).toHaveBeenCalled();
        expect(naturePlanService.addNaturePlanToCollectionIfMissing).toHaveBeenCalledWith(naturePlanCollection, ...additionalNaturePlans);
        expect(comp.naturePlansSharedCollection).toEqual(expectedCollection);
      });

      it('Should update editForm', () => {
        const movementBank: IMovementBank = { id: 456 };
        const accountBank: IAccountBank = { id: 42599 };
        movementBank.accountBank = accountBank;
        const naturePlan: INaturePlan = { id: 72966 };
        movementBank.naturePlan = naturePlan;

        activatedRoute.data = of({ movementBank });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(movementBank));
        expect(comp.accountBanksSharedCollection).toContain(accountBank);
        expect(comp.naturePlansSharedCollection).toContain(naturePlan);
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<MovementBank>>();
        const movementBank = { id: 123 };
        jest.spyOn(movementBankService, 'update').mockReturnValue(saveSubject);
        jest.spyOn(comp, 'previousState');
        activatedRoute.data = of({ movementBank });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: movementBank }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(movementBankService.update).toHaveBeenCalledWith(movementBank);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<MovementBank>>();
        const movementBank = new MovementBank();
        jest.spyOn(movementBankService, 'create').mockReturnValue(saveSubject);
        jest.spyOn(comp, 'previousState');
        activatedRoute.data = of({ movementBank });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: movementBank }));
        saveSubject.complete();

        // THEN
        expect(movementBankService.create).toHaveBeenCalledWith(movementBank);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<MovementBank>>();
        const movementBank = { id: 123 };
        jest.spyOn(movementBankService, 'update').mockReturnValue(saveSubject);
        jest.spyOn(comp, 'previousState');
        activatedRoute.data = of({ movementBank });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(movementBankService.update).toHaveBeenCalledWith(movementBank);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });

    describe('Tracking relationships identifiers', () => {
      describe('trackAccountBankById', () => {
        it('Should return tracked AccountBank primary key', () => {
          const entity = { id: 123 };
          const trackResult = comp.trackAccountBankById(0, entity);
          expect(trackResult).toEqual(entity.id);
        });
      });

      describe('trackNaturePlanById', () => {
        it('Should return tracked NaturePlan primary key', () => {
          const entity = { id: 123 };
          const trackResult = comp.trackNaturePlanById(0, entity);
          expect(trackResult).toEqual(entity.id);
        });
      });
    });
  });
});

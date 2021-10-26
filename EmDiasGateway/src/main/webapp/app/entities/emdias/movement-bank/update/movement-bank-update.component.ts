import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import * as dayjs from 'dayjs';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';

import { IMovementBank, MovementBank } from '../movement-bank.model';
import { MovementBankService } from '../service/movement-bank.service';
import { IAccountBank } from 'app/entities/emdias/account-bank/account-bank.model';
import { AccountBankService } from 'app/entities/emdias/account-bank/service/account-bank.service';
import { INaturePlan } from 'app/entities/emdias/nature-plan/nature-plan.model';
import { NaturePlanService } from 'app/entities/emdias/nature-plan/service/nature-plan.service';

@Component({
  selector: 'jhi-movement-bank-update',
  templateUrl: './movement-bank-update.component.html',
})
export class MovementBankUpdateComponent implements OnInit {
  isSaving = false;

  accountBanksSharedCollection: IAccountBank[] = [];
  naturePlansSharedCollection: INaturePlan[] = [];

  editForm = this.fb.group({
    id: [],
    movementDate: [],
    movementValue: [],
    history: [],
    numberDoc: [],
    accid: [],
    accountBank: [],
    naturePlan: [],
  });

  constructor(
    protected movementBankService: MovementBankService,
    protected accountBankService: AccountBankService,
    protected naturePlanService: NaturePlanService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ movementBank }) => {
      if (movementBank.id === undefined) {
        const today = dayjs().startOf('day');
        movementBank.movementDate = today;
      }

      this.updateForm(movementBank);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const movementBank = this.createFromForm();
    if (movementBank.id !== undefined) {
      this.subscribeToSaveResponse(this.movementBankService.update(movementBank));
    } else {
      this.subscribeToSaveResponse(this.movementBankService.create(movementBank));
    }
  }

  trackAccountBankById(index: number, item: IAccountBank): number {
    return item.id!;
  }

  trackNaturePlanById(index: number, item: INaturePlan): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMovementBank>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(movementBank: IMovementBank): void {
    this.editForm.patchValue({
      id: movementBank.id,
      movementDate: movementBank.movementDate ? movementBank.movementDate.format(DATE_TIME_FORMAT) : null,
      movementValue: movementBank.movementValue,
      history: movementBank.history,
      numberDoc: movementBank.numberDoc,
      accid: movementBank.accid,
      accountBank: movementBank.accountBank,
      naturePlan: movementBank.naturePlan,
    });

    this.accountBanksSharedCollection = this.accountBankService.addAccountBankToCollectionIfMissing(
      this.accountBanksSharedCollection,
      movementBank.accountBank
    );
    this.naturePlansSharedCollection = this.naturePlanService.addNaturePlanToCollectionIfMissing(
      this.naturePlansSharedCollection,
      movementBank.naturePlan
    );
  }

  protected loadRelationshipsOptions(): void {
    this.accountBankService
      .query()
      .pipe(map((res: HttpResponse<IAccountBank[]>) => res.body ?? []))
      .pipe(
        map((accountBanks: IAccountBank[]) =>
          this.accountBankService.addAccountBankToCollectionIfMissing(accountBanks, this.editForm.get('accountBank')!.value)
        )
      )
      .subscribe((accountBanks: IAccountBank[]) => (this.accountBanksSharedCollection = accountBanks));

    this.naturePlanService
      .query()
      .pipe(map((res: HttpResponse<INaturePlan[]>) => res.body ?? []))
      .pipe(
        map((naturePlans: INaturePlan[]) =>
          this.naturePlanService.addNaturePlanToCollectionIfMissing(naturePlans, this.editForm.get('naturePlan')!.value)
        )
      )
      .subscribe((naturePlans: INaturePlan[]) => (this.naturePlansSharedCollection = naturePlans));
  }

  protected createFromForm(): IMovementBank {
    return {
      ...new MovementBank(),
      id: this.editForm.get(['id'])!.value,
      movementDate: this.editForm.get(['movementDate'])!.value
        ? dayjs(this.editForm.get(['movementDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      movementValue: this.editForm.get(['movementValue'])!.value,
      history: this.editForm.get(['history'])!.value,
      numberDoc: this.editForm.get(['numberDoc'])!.value,
      accid: this.editForm.get(['accid'])!.value,
      accountBank: this.editForm.get(['accountBank'])!.value,
      naturePlan: this.editForm.get(['naturePlan'])!.value,
    };
  }
}

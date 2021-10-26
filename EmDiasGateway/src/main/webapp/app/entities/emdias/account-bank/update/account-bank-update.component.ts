import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { IAccountBank, AccountBank } from '../account-bank.model';
import { AccountBankService } from '../service/account-bank.service';
import { IWorkSpace } from 'app/entities/emdias/work-space/work-space.model';
import { WorkSpaceService } from 'app/entities/emdias/work-space/service/work-space.service';

@Component({
  selector: 'jhi-account-bank-update',
  templateUrl: './account-bank-update.component.html',
})
export class AccountBankUpdateComponent implements OnInit {
  isSaving = false;

  workSpacesSharedCollection: IWorkSpace[] = [];

  editForm = this.fb.group({
    id: [],
    nomeDaContaBancaria: [],
    valorInicial: [],
    codContaExtrato: [],
    digito: [],
    workSpace: [],
  });

  constructor(
    protected accountBankService: AccountBankService,
    protected workSpaceService: WorkSpaceService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ accountBank }) => {
      this.updateForm(accountBank);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const accountBank = this.createFromForm();
    if (accountBank.id !== undefined) {
      this.subscribeToSaveResponse(this.accountBankService.update(accountBank));
    } else {
      this.subscribeToSaveResponse(this.accountBankService.create(accountBank));
    }
  }

  trackWorkSpaceById(index: number, item: IWorkSpace): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAccountBank>>): void {
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

  protected updateForm(accountBank: IAccountBank): void {
    this.editForm.patchValue({
      id: accountBank.id,
      nomeDaContaBancaria: accountBank.nomeDaContaBancaria,
      valorInicial: accountBank.valorInicial,
      codContaExtrato: accountBank.codContaExtrato,
      digito: accountBank.digito,
      workSpace: accountBank.workSpace,
    });

    this.workSpacesSharedCollection = this.workSpaceService.addWorkSpaceToCollectionIfMissing(
      this.workSpacesSharedCollection,
      accountBank.workSpace
    );
  }

  protected loadRelationshipsOptions(): void {
    this.workSpaceService
      .query()
      .pipe(map((res: HttpResponse<IWorkSpace[]>) => res.body ?? []))
      .pipe(
        map((workSpaces: IWorkSpace[]) =>
          this.workSpaceService.addWorkSpaceToCollectionIfMissing(workSpaces, this.editForm.get('workSpace')!.value)
        )
      )
      .subscribe((workSpaces: IWorkSpace[]) => (this.workSpacesSharedCollection = workSpaces));
  }

  protected createFromForm(): IAccountBank {
    return {
      ...new AccountBank(),
      id: this.editForm.get(['id'])!.value,
      nomeDaContaBancaria: this.editForm.get(['nomeDaContaBancaria'])!.value,
      valorInicial: this.editForm.get(['valorInicial'])!.value,
      codContaExtrato: this.editForm.get(['codContaExtrato'])!.value,
      digito: this.editForm.get(['digito'])!.value,
      workSpace: this.editForm.get(['workSpace'])!.value,
    };
  }
}

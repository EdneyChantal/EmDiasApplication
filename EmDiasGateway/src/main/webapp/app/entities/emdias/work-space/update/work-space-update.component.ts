import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { IWorkSpace, WorkSpace } from '../work-space.model';
import { WorkSpaceService } from '../service/work-space.service';

@Component({
  selector: 'jhi-work-space-update',
  templateUrl: './work-space-update.component.html',
})
export class WorkSpaceUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nome: [],
  });

  constructor(protected workSpaceService: WorkSpaceService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ workSpace }) => {
      this.updateForm(workSpace);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const workSpace = this.createFromForm();
    if (workSpace.id !== undefined) {
      this.subscribeToSaveResponse(this.workSpaceService.update(workSpace));
    } else {
      this.subscribeToSaveResponse(this.workSpaceService.create(workSpace));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IWorkSpace>>): void {
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

  protected updateForm(workSpace: IWorkSpace): void {
    this.editForm.patchValue({
      id: workSpace.id,
      nome: workSpace.nome,
    });
  }

  protected createFromForm(): IWorkSpace {
    return {
      ...new WorkSpace(),
      id: this.editForm.get(['id'])!.value,
      nome: this.editForm.get(['nome'])!.value,
    };
  }
}

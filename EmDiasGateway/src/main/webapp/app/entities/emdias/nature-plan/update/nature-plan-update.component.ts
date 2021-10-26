import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { INaturePlan, NaturePlan } from '../nature-plan.model';
import { NaturePlanService } from '../service/nature-plan.service';
import { IWorkSpace } from 'app/entities/emdias/work-space/work-space.model';
import { WorkSpaceService } from 'app/entities/emdias/work-space/service/work-space.service';

@Component({
  selector: 'jhi-nature-plan-update',
  templateUrl: './nature-plan-update.component.html',
})
export class NaturePlanUpdateComponent implements OnInit {
  isSaving = false;

  workSpacesSharedCollection: IWorkSpace[] = [];

  editForm = this.fb.group({
    id: [],
    descNaturePlan: [],
    typeNaturePlan: [],
    workSpace: [],
  });

  constructor(
    protected naturePlanService: NaturePlanService,
    protected workSpaceService: WorkSpaceService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ naturePlan }) => {
      this.updateForm(naturePlan);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const naturePlan = this.createFromForm();
    if (naturePlan.id !== undefined) {
      this.subscribeToSaveResponse(this.naturePlanService.update(naturePlan));
    } else {
      this.subscribeToSaveResponse(this.naturePlanService.create(naturePlan));
    }
  }

  trackWorkSpaceById(index: number, item: IWorkSpace): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<INaturePlan>>): void {
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

  protected updateForm(naturePlan: INaturePlan): void {
    this.editForm.patchValue({
      id: naturePlan.id,
      descNaturePlan: naturePlan.descNaturePlan,
      typeNaturePlan: naturePlan.typeNaturePlan,
      workSpace: naturePlan.workSpace,
    });

    this.workSpacesSharedCollection = this.workSpaceService.addWorkSpaceToCollectionIfMissing(
      this.workSpacesSharedCollection,
      naturePlan.workSpace
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

  protected createFromForm(): INaturePlan {
    return {
      ...new NaturePlan(),
      id: this.editForm.get(['id'])!.value,
      descNaturePlan: this.editForm.get(['descNaturePlan'])!.value,
      typeNaturePlan: this.editForm.get(['typeNaturePlan'])!.value,
      workSpace: this.editForm.get(['workSpace'])!.value,
    };
  }
}

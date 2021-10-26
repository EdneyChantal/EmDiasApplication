import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { INaturePlan } from '../nature-plan.model';
import { NaturePlanService } from '../service/nature-plan.service';
import { NaturePlanDeleteDialogComponent } from '../delete/nature-plan-delete-dialog.component';

@Component({
  selector: 'jhi-nature-plan',
  templateUrl: './nature-plan.component.html',
})
export class NaturePlanComponent implements OnInit {
  naturePlans?: INaturePlan[];
  isLoading = false;

  constructor(protected naturePlanService: NaturePlanService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.naturePlanService.query().subscribe(
      (res: HttpResponse<INaturePlan[]>) => {
        this.isLoading = false;
        this.naturePlans = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(index: number, item: INaturePlan): number {
    return item.id!;
  }

  delete(naturePlan: INaturePlan): void {
    const modalRef = this.modalService.open(NaturePlanDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.naturePlan = naturePlan;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}

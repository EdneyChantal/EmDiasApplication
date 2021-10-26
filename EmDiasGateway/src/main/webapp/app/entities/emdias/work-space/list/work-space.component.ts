import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IWorkSpace } from '../work-space.model';
import { WorkSpaceService } from '../service/work-space.service';
import { WorkSpaceDeleteDialogComponent } from '../delete/work-space-delete-dialog.component';

@Component({
  selector: 'jhi-work-space',
  templateUrl: './work-space.component.html',
})
export class WorkSpaceComponent implements OnInit {
  workSpaces?: IWorkSpace[];
  isLoading = false;

  constructor(protected workSpaceService: WorkSpaceService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.workSpaceService.query().subscribe(
      (res: HttpResponse<IWorkSpace[]>) => {
        this.isLoading = false;
        this.workSpaces = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(index: number, item: IWorkSpace): number {
    return item.id!;
  }

  delete(workSpace: IWorkSpace): void {
    const modalRef = this.modalService.open(WorkSpaceDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.workSpace = workSpace;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}

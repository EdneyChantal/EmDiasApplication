import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IWorkSpace } from '../work-space.model';
import { WorkSpaceService } from '../service/work-space.service';

@Component({
  templateUrl: './work-space-delete-dialog.component.html',
})
export class WorkSpaceDeleteDialogComponent {
  workSpace?: IWorkSpace;

  constructor(protected workSpaceService: WorkSpaceService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.workSpaceService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}

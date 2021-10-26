import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { INaturePlan } from '../nature-plan.model';
import { NaturePlanService } from '../service/nature-plan.service';

@Component({
  templateUrl: './nature-plan-delete-dialog.component.html',
})
export class NaturePlanDeleteDialogComponent {
  naturePlan?: INaturePlan;

  constructor(protected naturePlanService: NaturePlanService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.naturePlanService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}

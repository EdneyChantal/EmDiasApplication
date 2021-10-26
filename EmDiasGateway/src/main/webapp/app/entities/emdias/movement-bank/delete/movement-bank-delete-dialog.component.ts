import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IMovementBank } from '../movement-bank.model';
import { MovementBankService } from '../service/movement-bank.service';

@Component({
  templateUrl: './movement-bank-delete-dialog.component.html',
})
export class MovementBankDeleteDialogComponent {
  movementBank?: IMovementBank;

  constructor(protected movementBankService: MovementBankService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.movementBankService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}

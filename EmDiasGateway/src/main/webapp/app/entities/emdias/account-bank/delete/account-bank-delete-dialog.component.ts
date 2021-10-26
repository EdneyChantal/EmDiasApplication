import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IAccountBank } from '../account-bank.model';
import { AccountBankService } from '../service/account-bank.service';

@Component({
  templateUrl: './account-bank-delete-dialog.component.html',
})
export class AccountBankDeleteDialogComponent {
  accountBank?: IAccountBank;

  constructor(protected accountBankService: AccountBankService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.accountBankService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}

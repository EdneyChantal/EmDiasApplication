import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IAccountBank } from '../account-bank.model';
import { AccountBankService } from '../service/account-bank.service';
import { AccountBankDeleteDialogComponent } from '../delete/account-bank-delete-dialog.component';

@Component({
  selector: 'jhi-account-bank',
  templateUrl: './account-bank.component.html',
})
export class AccountBankComponent implements OnInit {
  accountBanks?: IAccountBank[];
  isLoading = false;

  constructor(protected accountBankService: AccountBankService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.accountBankService.query().subscribe(
      (res: HttpResponse<IAccountBank[]>) => {
        this.isLoading = false;
        this.accountBanks = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(index: number, item: IAccountBank): number {
    return item.id!;
  }

  delete(accountBank: IAccountBank): void {
    const modalRef = this.modalService.open(AccountBankDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.accountBank = accountBank;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}

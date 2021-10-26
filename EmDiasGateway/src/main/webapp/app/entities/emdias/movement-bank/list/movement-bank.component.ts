import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IMovementBank } from '../movement-bank.model';
import { MovementBankService } from '../service/movement-bank.service';
import { MovementBankDeleteDialogComponent } from '../delete/movement-bank-delete-dialog.component';

@Component({
  selector: 'jhi-movement-bank',
  templateUrl: './movement-bank.component.html',
})
export class MovementBankComponent implements OnInit {
  movementBanks?: IMovementBank[];
  isLoading = false;

  constructor(protected movementBankService: MovementBankService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.movementBankService.query().subscribe(
      (res: HttpResponse<IMovementBank[]>) => {
        this.isLoading = false;
        this.movementBanks = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(index: number, item: IMovementBank): number {
    return item.id!;
  }

  delete(movementBank: IMovementBank): void {
    const modalRef = this.modalService.open(MovementBankDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.movementBank = movementBank;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}

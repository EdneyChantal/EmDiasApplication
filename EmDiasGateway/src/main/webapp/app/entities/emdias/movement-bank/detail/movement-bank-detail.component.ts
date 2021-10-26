import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMovementBank } from '../movement-bank.model';

@Component({
  selector: 'jhi-movement-bank-detail',
  templateUrl: './movement-bank-detail.component.html',
})
export class MovementBankDetailComponent implements OnInit {
  movementBank: IMovementBank | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ movementBank }) => {
      this.movementBank = movementBank;
    });
  }

  previousState(): void {
    window.history.back();
  }
}

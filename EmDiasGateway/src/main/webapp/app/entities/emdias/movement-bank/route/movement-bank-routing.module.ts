import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { MovementBankComponent } from '../list/movement-bank.component';
import { MovementBankDetailComponent } from '../detail/movement-bank-detail.component';
import { MovementBankUpdateComponent } from '../update/movement-bank-update.component';
import { MovementBankRoutingResolveService } from './movement-bank-routing-resolve.service';

const movementBankRoute: Routes = [
  {
    path: '',
    component: MovementBankComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: MovementBankDetailComponent,
    resolve: {
      movementBank: MovementBankRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: MovementBankUpdateComponent,
    resolve: {
      movementBank: MovementBankRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: MovementBankUpdateComponent,
    resolve: {
      movementBank: MovementBankRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(movementBankRoute)],
  exports: [RouterModule],
})
export class MovementBankRoutingModule {}

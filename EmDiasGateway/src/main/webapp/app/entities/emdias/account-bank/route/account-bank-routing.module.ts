import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { AccountBankComponent } from '../list/account-bank.component';
import { AccountBankDetailComponent } from '../detail/account-bank-detail.component';
import { AccountBankUpdateComponent } from '../update/account-bank-update.component';
import { AccountBankRoutingResolveService } from './account-bank-routing-resolve.service';

const accountBankRoute: Routes = [
  {
    path: '',
    component: AccountBankComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AccountBankDetailComponent,
    resolve: {
      accountBank: AccountBankRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AccountBankUpdateComponent,
    resolve: {
      accountBank: AccountBankRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AccountBankUpdateComponent,
    resolve: {
      accountBank: AccountBankRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(accountBankRoute)],
  exports: [RouterModule],
})
export class AccountBankRoutingModule {}

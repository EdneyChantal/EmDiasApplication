import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { NaturePlanComponent } from '../list/nature-plan.component';
import { NaturePlanDetailComponent } from '../detail/nature-plan-detail.component';
import { NaturePlanUpdateComponent } from '../update/nature-plan-update.component';
import { NaturePlanRoutingResolveService } from './nature-plan-routing-resolve.service';

const naturePlanRoute: Routes = [
  {
    path: '',
    component: NaturePlanComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: NaturePlanDetailComponent,
    resolve: {
      naturePlan: NaturePlanRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: NaturePlanUpdateComponent,
    resolve: {
      naturePlan: NaturePlanRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: NaturePlanUpdateComponent,
    resolve: {
      naturePlan: NaturePlanRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(naturePlanRoute)],
  exports: [RouterModule],
})
export class NaturePlanRoutingModule {}

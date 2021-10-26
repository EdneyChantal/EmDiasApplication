import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { WorkSpaceComponent } from '../list/work-space.component';
import { WorkSpaceDetailComponent } from '../detail/work-space-detail.component';
import { WorkSpaceUpdateComponent } from '../update/work-space-update.component';
import { WorkSpaceRoutingResolveService } from './work-space-routing-resolve.service';

const workSpaceRoute: Routes = [
  {
    path: '',
    component: WorkSpaceComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: WorkSpaceDetailComponent,
    resolve: {
      workSpace: WorkSpaceRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: WorkSpaceUpdateComponent,
    resolve: {
      workSpace: WorkSpaceRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: WorkSpaceUpdateComponent,
    resolve: {
      workSpace: WorkSpaceRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(workSpaceRoute)],
  exports: [RouterModule],
})
export class WorkSpaceRoutingModule {}

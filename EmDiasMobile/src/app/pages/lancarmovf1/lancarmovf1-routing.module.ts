import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserRouteAccessService } from 'src/app/services/auth/user-route-access.service';
import { Lancarmovf1Page } from './lancarmovf1.page';

const routes: Routes = [
  {
    path: '',
    component: Lancarmovf1Page,
    data: {
      authorities: ['ROLE_USER'],
    },
    canActivate: [UserRouteAccessService]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class Lancarmovf1PageRoutingModule {}

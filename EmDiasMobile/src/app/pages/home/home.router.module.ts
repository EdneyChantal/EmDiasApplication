import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomePage } from './home.page';
import { UserRouteAccessService } from 'src/app/services/auth/user-route-access.service';

const routes: Routes = [
  {
    path: '',
    component: HomePage,
    data: {
        authorities: ['ROLE_USER'],
    },
    canActivate: [UserRouteAccessService],
    children: [
      {
        path: 'home',
        children: [
          {
            path: '',
            loadChildren: () => import('../home/home.module').then((m) => m.HomePageModule),
          },
        ],
      },
      {
        path: 'lancarmovf1',
        children: [
          {
            path: '',
            loadChildren: () => import('../lancarmovf1/lancarmovf1.module').then((m) => m.Lancarmovf1PageModule),
          },
        ],
      },
      {
        path: '',
        redirectTo: '/tabs/home',
        pathMatch: 'full',
      },
      {
        path: 'lancarmovf2/:idconta',
        loadChildren: () => import('../lancarmovf2/lancarmovf2.module').then( m => m.Lancarmovf2PageModule)
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class HomePageRoutingModule {}

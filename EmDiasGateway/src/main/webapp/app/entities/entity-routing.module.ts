import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'work-space',
        data: { pageTitle: 'emdiasgatewayApp.emdiasWorkSpace.home.title' },
        loadChildren: () => import('./emdias/work-space/work-space.module').then(m => m.EmdiasWorkSpaceModule),
      },
      {
        path: 'nature-plan',
        data: { pageTitle: 'emdiasgatewayApp.emdiasNaturePlan.home.title' },
        loadChildren: () => import('./emdias/nature-plan/nature-plan.module').then(m => m.EmdiasNaturePlanModule),
      },
      {
        path: 'account-bank',
        data: { pageTitle: 'emdiasgatewayApp.emdiasAccountBank.home.title' },
        loadChildren: () => import('./emdias/account-bank/account-bank.module').then(m => m.EmdiasAccountBankModule),
      },
      {
        path: 'movement-bank',
        data: { pageTitle: 'emdiasgatewayApp.emdiasMovementBank.home.title' },
        loadChildren: () => import('./emdias/movement-bank/movement-bank.module').then(m => m.EmdiasMovementBankModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}

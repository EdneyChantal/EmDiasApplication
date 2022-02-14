import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { Lancarmovf2Page } from './lancarmovf2.page';

const routes: Routes = [
  {
    path: '',
    component: Lancarmovf2Page
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class Lancarmovf2PageRoutingModule {}

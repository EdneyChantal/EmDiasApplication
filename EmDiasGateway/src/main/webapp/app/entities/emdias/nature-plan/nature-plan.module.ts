import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { NaturePlanComponent } from './list/nature-plan.component';
import { NaturePlanDetailComponent } from './detail/nature-plan-detail.component';
import { NaturePlanUpdateComponent } from './update/nature-plan-update.component';
import { NaturePlanDeleteDialogComponent } from './delete/nature-plan-delete-dialog.component';
import { NaturePlanRoutingModule } from './route/nature-plan-routing.module';

@NgModule({
  imports: [SharedModule, NaturePlanRoutingModule],
  declarations: [NaturePlanComponent, NaturePlanDetailComponent, NaturePlanUpdateComponent, NaturePlanDeleteDialogComponent],
  entryComponents: [NaturePlanDeleteDialogComponent],
})
export class EmdiasNaturePlanModule {}

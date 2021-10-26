import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { WorkSpaceComponent } from './list/work-space.component';
import { WorkSpaceDetailComponent } from './detail/work-space-detail.component';
import { WorkSpaceUpdateComponent } from './update/work-space-update.component';
import { WorkSpaceDeleteDialogComponent } from './delete/work-space-delete-dialog.component';
import { WorkSpaceRoutingModule } from './route/work-space-routing.module';

@NgModule({
  imports: [SharedModule, WorkSpaceRoutingModule],
  declarations: [WorkSpaceComponent, WorkSpaceDetailComponent, WorkSpaceUpdateComponent, WorkSpaceDeleteDialogComponent],
  entryComponents: [WorkSpaceDeleteDialogComponent],
})
export class EmdiasWorkSpaceModule {}

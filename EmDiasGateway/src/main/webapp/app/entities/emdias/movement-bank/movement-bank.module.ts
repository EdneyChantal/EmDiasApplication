import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { MovementBankComponent } from './list/movement-bank.component';
import { MovementBankDetailComponent } from './detail/movement-bank-detail.component';
import { MovementBankUpdateComponent } from './update/movement-bank-update.component';
import { MovementBankDeleteDialogComponent } from './delete/movement-bank-delete-dialog.component';
import { MovementBankRoutingModule } from './route/movement-bank-routing.module';

@NgModule({
  imports: [SharedModule, MovementBankRoutingModule],
  declarations: [MovementBankComponent, MovementBankDetailComponent, MovementBankUpdateComponent, MovementBankDeleteDialogComponent],
  entryComponents: [MovementBankDeleteDialogComponent],
})
export class EmdiasMovementBankModule {}

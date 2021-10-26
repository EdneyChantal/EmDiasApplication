import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { AccountBankComponent } from './list/account-bank.component';
import { AccountBankDetailComponent } from './detail/account-bank-detail.component';
import { AccountBankUpdateComponent } from './update/account-bank-update.component';
import { AccountBankDeleteDialogComponent } from './delete/account-bank-delete-dialog.component';
import { AccountBankRoutingModule } from './route/account-bank-routing.module';

@NgModule({
  imports: [SharedModule, AccountBankRoutingModule],
  declarations: [AccountBankComponent, AccountBankDetailComponent, AccountBankUpdateComponent, AccountBankDeleteDialogComponent],
  entryComponents: [AccountBankDeleteDialogComponent],
})
export class EmdiasAccountBankModule {}

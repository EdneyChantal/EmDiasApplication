import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SharedModule } from 'app/shared/shared.module';
import { HOME_ROUTE } from './home.route';
import { HomeComponent } from './home.component';
import {FluxoCaixaModule} from 'app/entities/emdias/fluxo-caixa/fluxo-caixa.module'

@NgModule({
  imports: [SharedModule,FluxoCaixaModule, RouterModule.forChild([HOME_ROUTE])],
  declarations: [HomeComponent],
})
export class HomeModule {}

import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import {FluxoCaixaComponent} from './list/fluxo-caixa.component';

@NgModule({
  imports: [SharedModule],
  declarations: [FluxoCaixaComponent],
  exports:[FluxoCaixaComponent]
})
export class FluxoCaixaModule {}

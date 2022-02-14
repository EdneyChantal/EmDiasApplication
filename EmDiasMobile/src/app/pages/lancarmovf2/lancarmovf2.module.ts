import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { Lancarmovf2PageRoutingModule } from './lancarmovf2-routing.module';

import { Lancarmovf2Page } from './lancarmovf2.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    Lancarmovf2PageRoutingModule
  ],
  declarations: [Lancarmovf2Page]
})
export class Lancarmovf2PageModule {}

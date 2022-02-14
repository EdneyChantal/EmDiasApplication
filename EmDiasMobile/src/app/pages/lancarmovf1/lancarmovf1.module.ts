import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { Lancarmovf1PageRoutingModule } from './lancarmovf1-routing.module';

import { Lancarmovf1Page } from './lancarmovf1.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    Lancarmovf1PageRoutingModule
  ],
  declarations: [Lancarmovf1Page]
})
export class Lancarmovf1PageModule {}

import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { IonicModule } from '@ionic/angular';
import { TranslateModule } from '@ngx-translate/core';
import { UserRouteAccessService } from 'src/app/services/auth/user-route-access.service';
import { HomePage } from './home.page';
import { HomePageRoutingModule } from './home.router.module';

/*const routes: Routes = [
  {
    path: '',
    component: HomePage,
    data: {
      authorities: ['ROLE_USER'],
    },
    canActivate: [UserRouteAccessService],
  },
];*/

@NgModule({
  imports: [IonicModule, CommonModule, FormsModule, TranslateModule, HomePageRoutingModule],
  declarations: [HomePage],
})
export class HomePageModule {}

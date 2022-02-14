import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: '', loadChildren: () => import('./pages/welcome/welcome.module').then((m) => m.WelcomePageModule) },
  { path: 'tabs', loadChildren: () => import('./pages/tabs/tabs.module').then((m) => m.TabsPageModule) },
  { path: 'login', loadChildren: () => import('./pages/login/login.module').then((m) => m.LoginPageModule) },
  { path: 'signup', loadChildren: () => import('./pages/signup/signup.module').then((m) => m.SignupPageModule) },
  /*{ path: 'lancarmovf1', loadChildren: () => import('./pages/lancarmovf1/lancarmovf1.module').then((m) => m.Lancarmovf1PageModule) },*/
  { path: 'accessdenied', redirectTo: '', pathMatch: 'full' },
  {
    path: 'lancarmovf2',
    loadChildren: () => import('./pages/lancarmovf2/lancarmovf2.module').then( m => m.Lancarmovf2PageModule)
  }
  
];
@NgModule({
  imports: [RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })],
  exports: [RouterModule],
})
export class AppRoutingModule {}

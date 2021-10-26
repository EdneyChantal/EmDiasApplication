import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IAccountBank, AccountBank } from '../account-bank.model';
import { AccountBankService } from '../service/account-bank.service';

@Injectable({ providedIn: 'root' })
export class AccountBankRoutingResolveService implements Resolve<IAccountBank> {
  constructor(protected service: AccountBankService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAccountBank> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((accountBank: HttpResponse<AccountBank>) => {
          if (accountBank.body) {
            return of(accountBank.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new AccountBank());
  }
}

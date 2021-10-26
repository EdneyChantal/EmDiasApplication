import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IMovementBank, MovementBank } from '../movement-bank.model';
import { MovementBankService } from '../service/movement-bank.service';

@Injectable({ providedIn: 'root' })
export class MovementBankRoutingResolveService implements Resolve<IMovementBank> {
  constructor(protected service: MovementBankService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMovementBank> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((movementBank: HttpResponse<MovementBank>) => {
          if (movementBank.body) {
            return of(movementBank.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new MovementBank());
  }
}

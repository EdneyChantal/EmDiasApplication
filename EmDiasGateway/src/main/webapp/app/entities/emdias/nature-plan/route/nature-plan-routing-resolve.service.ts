import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { INaturePlan, NaturePlan } from '../nature-plan.model';
import { NaturePlanService } from '../service/nature-plan.service';

@Injectable({ providedIn: 'root' })
export class NaturePlanRoutingResolveService implements Resolve<INaturePlan> {
  constructor(protected service: NaturePlanService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<INaturePlan> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((naturePlan: HttpResponse<NaturePlan>) => {
          if (naturePlan.body) {
            return of(naturePlan.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new NaturePlan());
  }
}

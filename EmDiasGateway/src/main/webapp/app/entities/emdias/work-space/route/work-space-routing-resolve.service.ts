import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IWorkSpace, WorkSpace } from '../work-space.model';
import { WorkSpaceService } from '../service/work-space.service';

@Injectable({ providedIn: 'root' })
export class WorkSpaceRoutingResolveService implements Resolve<IWorkSpace> {
  constructor(protected service: WorkSpaceService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IWorkSpace> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((workSpace: HttpResponse<WorkSpace>) => {
          if (workSpace.body) {
            return of(workSpace.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new WorkSpace());
  }
}

import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { INaturePlan, getNaturePlanIdentifier } from '../nature-plan.model';

export type EntityResponseType = HttpResponse<INaturePlan>;
export type EntityArrayResponseType = HttpResponse<INaturePlan[]>;

@Injectable({ providedIn: 'root' })
export class NaturePlanService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/nature-plans', 'emdias');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(naturePlan: INaturePlan): Observable<EntityResponseType> {
    return this.http.post<INaturePlan>(this.resourceUrl, naturePlan, { observe: 'response' });
  }

  update(naturePlan: INaturePlan): Observable<EntityResponseType> {
    return this.http.put<INaturePlan>(`${this.resourceUrl}/${getNaturePlanIdentifier(naturePlan) as number}`, naturePlan, {
      observe: 'response',
    });
  }

  partialUpdate(naturePlan: INaturePlan): Observable<EntityResponseType> {
    return this.http.patch<INaturePlan>(`${this.resourceUrl}/${getNaturePlanIdentifier(naturePlan) as number}`, naturePlan, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<INaturePlan>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<INaturePlan[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addNaturePlanToCollectionIfMissing(
    naturePlanCollection: INaturePlan[],
    ...naturePlansToCheck: (INaturePlan | null | undefined)[]
  ): INaturePlan[] {
    const naturePlans: INaturePlan[] = naturePlansToCheck.filter(isPresent);
    if (naturePlans.length > 0) {
      const naturePlanCollectionIdentifiers = naturePlanCollection.map(naturePlanItem => getNaturePlanIdentifier(naturePlanItem)!);
      const naturePlansToAdd = naturePlans.filter(naturePlanItem => {
        const naturePlanIdentifier = getNaturePlanIdentifier(naturePlanItem);
        if (naturePlanIdentifier == null || naturePlanCollectionIdentifiers.includes(naturePlanIdentifier)) {
          return false;
        }
        naturePlanCollectionIdentifiers.push(naturePlanIdentifier);
        return true;
      });
      return [...naturePlansToAdd, ...naturePlanCollection];
    }
    return naturePlanCollection;
  }
}

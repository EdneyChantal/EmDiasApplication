import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IWorkSpace, getWorkSpaceIdentifier } from '../work-space.model';

export type EntityResponseType = HttpResponse<IWorkSpace>;
export type EntityArrayResponseType = HttpResponse<IWorkSpace[]>;

@Injectable({ providedIn: 'root' })
export class WorkSpaceService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/work-spaces', 'emdias');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(workSpace: IWorkSpace): Observable<EntityResponseType> {
    return this.http.post<IWorkSpace>(this.resourceUrl, workSpace, { observe: 'response' });
  }

  update(workSpace: IWorkSpace): Observable<EntityResponseType> {
    return this.http.put<IWorkSpace>(`${this.resourceUrl}/${getWorkSpaceIdentifier(workSpace) as number}`, workSpace, {
      observe: 'response',
    });
  }

  partialUpdate(workSpace: IWorkSpace): Observable<EntityResponseType> {
    return this.http.patch<IWorkSpace>(`${this.resourceUrl}/${getWorkSpaceIdentifier(workSpace) as number}`, workSpace, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IWorkSpace>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IWorkSpace[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addWorkSpaceToCollectionIfMissing(
    workSpaceCollection: IWorkSpace[],
    ...workSpacesToCheck: (IWorkSpace | null | undefined)[]
  ): IWorkSpace[] {
    const workSpaces: IWorkSpace[] = workSpacesToCheck.filter(isPresent);
    if (workSpaces.length > 0) {
      const workSpaceCollectionIdentifiers = workSpaceCollection.map(workSpaceItem => getWorkSpaceIdentifier(workSpaceItem)!);
      const workSpacesToAdd = workSpaces.filter(workSpaceItem => {
        const workSpaceIdentifier = getWorkSpaceIdentifier(workSpaceItem);
        if (workSpaceIdentifier == null || workSpaceCollectionIdentifiers.includes(workSpaceIdentifier)) {
          return false;
        }
        workSpaceCollectionIdentifiers.push(workSpaceIdentifier);
        return true;
      });
      return [...workSpacesToAdd, ...workSpaceCollection];
    }
    return workSpaceCollection;
  }
}

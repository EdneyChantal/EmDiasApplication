import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as dayjs from 'dayjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IMovementBank, getMovementBankIdentifier } from '../movement-bank.model';

export type EntityResponseType = HttpResponse<IMovementBank>;
export type EntityArrayResponseType = HttpResponse<IMovementBank[]>;

@Injectable({ providedIn: 'root' })
export class MovementBankService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/movement-banks', 'emdias');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(movementBank: IMovementBank): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(movementBank);
    return this.http
      .post<IMovementBank>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(movementBank: IMovementBank): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(movementBank);
    return this.http
      .put<IMovementBank>(`${this.resourceUrl}/${getMovementBankIdentifier(movementBank) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  partialUpdate(movementBank: IMovementBank): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(movementBank);
    return this.http
      .patch<IMovementBank>(`${this.resourceUrl}/${getMovementBankIdentifier(movementBank) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IMovementBank>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IMovementBank[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addMovementBankToCollectionIfMissing(
    movementBankCollection: IMovementBank[],
    ...movementBanksToCheck: (IMovementBank | null | undefined)[]
  ): IMovementBank[] {
    const movementBanks: IMovementBank[] = movementBanksToCheck.filter(isPresent);
    if (movementBanks.length > 0) {
      const movementBankCollectionIdentifiers = movementBankCollection.map(
        movementBankItem => getMovementBankIdentifier(movementBankItem)!
      );
      const movementBanksToAdd = movementBanks.filter(movementBankItem => {
        const movementBankIdentifier = getMovementBankIdentifier(movementBankItem);
        if (movementBankIdentifier == null || movementBankCollectionIdentifiers.includes(movementBankIdentifier)) {
          return false;
        }
        movementBankCollectionIdentifiers.push(movementBankIdentifier);
        return true;
      });
      return [...movementBanksToAdd, ...movementBankCollection];
    }
    return movementBankCollection;
  }

  protected convertDateFromClient(movementBank: IMovementBank): IMovementBank {
    return Object.assign({}, movementBank, {
      movementDate: movementBank.movementDate?.isValid() ? movementBank.movementDate.toJSON() : undefined,
    });
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.movementDate = res.body.movementDate ? dayjs(res.body.movementDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((movementBank: IMovementBank) => {
        movementBank.movementDate = movementBank.movementDate ? dayjs(movementBank.movementDate) : undefined;
      });
    }
    return res;
  }
}

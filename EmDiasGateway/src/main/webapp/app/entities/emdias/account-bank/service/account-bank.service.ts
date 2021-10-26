import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IAccountBank, getAccountBankIdentifier } from '../account-bank.model';

export type EntityResponseType = HttpResponse<IAccountBank>;
export type EntityArrayResponseType = HttpResponse<IAccountBank[]>;

@Injectable({ providedIn: 'root' })
export class AccountBankService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/account-banks', 'emdias');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(accountBank: IAccountBank): Observable<EntityResponseType> {
    return this.http.post<IAccountBank>(this.resourceUrl, accountBank, { observe: 'response' });
  }

  update(accountBank: IAccountBank): Observable<EntityResponseType> {
    return this.http.put<IAccountBank>(`${this.resourceUrl}/${getAccountBankIdentifier(accountBank) as number}`, accountBank, {
      observe: 'response',
    });
  }

  partialUpdate(accountBank: IAccountBank): Observable<EntityResponseType> {
    return this.http.patch<IAccountBank>(`${this.resourceUrl}/${getAccountBankIdentifier(accountBank) as number}`, accountBank, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAccountBank>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAccountBank[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addAccountBankToCollectionIfMissing(
    accountBankCollection: IAccountBank[],
    ...accountBanksToCheck: (IAccountBank | null | undefined)[]
  ): IAccountBank[] {
    const accountBanks: IAccountBank[] = accountBanksToCheck.filter(isPresent);
    if (accountBanks.length > 0) {
      const accountBankCollectionIdentifiers = accountBankCollection.map(accountBankItem => getAccountBankIdentifier(accountBankItem)!);
      const accountBanksToAdd = accountBanks.filter(accountBankItem => {
        const accountBankIdentifier = getAccountBankIdentifier(accountBankItem);
        if (accountBankIdentifier == null || accountBankCollectionIdentifiers.includes(accountBankIdentifier)) {
          return false;
        }
        accountBankCollectionIdentifiers.push(accountBankIdentifier);
        return true;
      });
      return [...accountBanksToAdd, ...accountBankCollection];
    }
    return accountBankCollection;
  }
}

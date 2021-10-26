import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IAccountBank, AccountBank } from '../account-bank.model';

import { AccountBankService } from './account-bank.service';

describe('Service Tests', () => {
  describe('AccountBank Service', () => {
    let service: AccountBankService;
    let httpMock: HttpTestingController;
    let elemDefault: IAccountBank;
    let expectedResult: IAccountBank | IAccountBank[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(AccountBankService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        id: 0,
        nomeDaContaBancaria: 'AAAAAAA',
        valorInicial: 0,
        codContaExtrato: 'AAAAAAA',
        digito: 'AAAAAAA',
      };
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a AccountBank', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new AccountBank()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a AccountBank', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            nomeDaContaBancaria: 'BBBBBB',
            valorInicial: 1,
            codContaExtrato: 'BBBBBB',
            digito: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a AccountBank', () => {
        const patchObject = Object.assign(
          {
            nomeDaContaBancaria: 'BBBBBB',
            valorInicial: 1,
            codContaExtrato: 'BBBBBB',
          },
          new AccountBank()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of AccountBank', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            nomeDaContaBancaria: 'BBBBBB',
            valorInicial: 1,
            codContaExtrato: 'BBBBBB',
            digito: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a AccountBank', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addAccountBankToCollectionIfMissing', () => {
        it('should add a AccountBank to an empty array', () => {
          const accountBank: IAccountBank = { id: 123 };
          expectedResult = service.addAccountBankToCollectionIfMissing([], accountBank);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(accountBank);
        });

        it('should not add a AccountBank to an array that contains it', () => {
          const accountBank: IAccountBank = { id: 123 };
          const accountBankCollection: IAccountBank[] = [
            {
              ...accountBank,
            },
            { id: 456 },
          ];
          expectedResult = service.addAccountBankToCollectionIfMissing(accountBankCollection, accountBank);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a AccountBank to an array that doesn't contain it", () => {
          const accountBank: IAccountBank = { id: 123 };
          const accountBankCollection: IAccountBank[] = [{ id: 456 }];
          expectedResult = service.addAccountBankToCollectionIfMissing(accountBankCollection, accountBank);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(accountBank);
        });

        it('should add only unique AccountBank to an array', () => {
          const accountBankArray: IAccountBank[] = [{ id: 123 }, { id: 456 }, { id: 62347 }];
          const accountBankCollection: IAccountBank[] = [{ id: 123 }];
          expectedResult = service.addAccountBankToCollectionIfMissing(accountBankCollection, ...accountBankArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const accountBank: IAccountBank = { id: 123 };
          const accountBank2: IAccountBank = { id: 456 };
          expectedResult = service.addAccountBankToCollectionIfMissing([], accountBank, accountBank2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(accountBank);
          expect(expectedResult).toContain(accountBank2);
        });

        it('should accept null and undefined values', () => {
          const accountBank: IAccountBank = { id: 123 };
          expectedResult = service.addAccountBankToCollectionIfMissing([], null, accountBank, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(accountBank);
        });

        it('should return initial array if no AccountBank is added', () => {
          const accountBankCollection: IAccountBank[] = [{ id: 123 }];
          expectedResult = service.addAccountBankToCollectionIfMissing(accountBankCollection, undefined, null);
          expect(expectedResult).toEqual(accountBankCollection);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});

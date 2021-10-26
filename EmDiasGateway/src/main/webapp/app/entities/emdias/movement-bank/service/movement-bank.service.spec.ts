import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as dayjs from 'dayjs';

import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IMovementBank, MovementBank } from '../movement-bank.model';

import { MovementBankService } from './movement-bank.service';

describe('Service Tests', () => {
  describe('MovementBank Service', () => {
    let service: MovementBankService;
    let httpMock: HttpTestingController;
    let elemDefault: IMovementBank;
    let expectedResult: IMovementBank | IMovementBank[] | boolean | null;
    let currentDate: dayjs.Dayjs;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(MovementBankService);
      httpMock = TestBed.inject(HttpTestingController);
      currentDate = dayjs();

      elemDefault = {
        id: 0,
        movementDate: currentDate,
        movementValue: 0,
        history: 'AAAAAAA',
        numberDoc: 'AAAAAAA',
        accid: 'AAAAAAA',
      };
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            movementDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a MovementBank', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            movementDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            movementDate: currentDate,
          },
          returnedFromService
        );

        service.create(new MovementBank()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a MovementBank', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            movementDate: currentDate.format(DATE_TIME_FORMAT),
            movementValue: 1,
            history: 'BBBBBB',
            numberDoc: 'BBBBBB',
            accid: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            movementDate: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a MovementBank', () => {
        const patchObject = Object.assign(
          {
            accid: 'BBBBBB',
          },
          new MovementBank()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            movementDate: currentDate,
          },
          returnedFromService
        );

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of MovementBank', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            movementDate: currentDate.format(DATE_TIME_FORMAT),
            movementValue: 1,
            history: 'BBBBBB',
            numberDoc: 'BBBBBB',
            accid: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            movementDate: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a MovementBank', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addMovementBankToCollectionIfMissing', () => {
        it('should add a MovementBank to an empty array', () => {
          const movementBank: IMovementBank = { id: 123 };
          expectedResult = service.addMovementBankToCollectionIfMissing([], movementBank);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(movementBank);
        });

        it('should not add a MovementBank to an array that contains it', () => {
          const movementBank: IMovementBank = { id: 123 };
          const movementBankCollection: IMovementBank[] = [
            {
              ...movementBank,
            },
            { id: 456 },
          ];
          expectedResult = service.addMovementBankToCollectionIfMissing(movementBankCollection, movementBank);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a MovementBank to an array that doesn't contain it", () => {
          const movementBank: IMovementBank = { id: 123 };
          const movementBankCollection: IMovementBank[] = [{ id: 456 }];
          expectedResult = service.addMovementBankToCollectionIfMissing(movementBankCollection, movementBank);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(movementBank);
        });

        it('should add only unique MovementBank to an array', () => {
          const movementBankArray: IMovementBank[] = [{ id: 123 }, { id: 456 }, { id: 62137 }];
          const movementBankCollection: IMovementBank[] = [{ id: 123 }];
          expectedResult = service.addMovementBankToCollectionIfMissing(movementBankCollection, ...movementBankArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const movementBank: IMovementBank = { id: 123 };
          const movementBank2: IMovementBank = { id: 456 };
          expectedResult = service.addMovementBankToCollectionIfMissing([], movementBank, movementBank2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(movementBank);
          expect(expectedResult).toContain(movementBank2);
        });

        it('should accept null and undefined values', () => {
          const movementBank: IMovementBank = { id: 123 };
          expectedResult = service.addMovementBankToCollectionIfMissing([], null, movementBank, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(movementBank);
        });

        it('should return initial array if no MovementBank is added', () => {
          const movementBankCollection: IMovementBank[] = [{ id: 123 }];
          expectedResult = service.addMovementBankToCollectionIfMissing(movementBankCollection, undefined, null);
          expect(expectedResult).toEqual(movementBankCollection);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});

import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { TypeNaturePlan } from 'app/entities/enumerations/type-nature-plan.model';
import { INaturePlan, NaturePlan } from '../nature-plan.model';

import { NaturePlanService } from './nature-plan.service';

describe('Service Tests', () => {
  describe('NaturePlan Service', () => {
    let service: NaturePlanService;
    let httpMock: HttpTestingController;
    let elemDefault: INaturePlan;
    let expectedResult: INaturePlan | INaturePlan[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(NaturePlanService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        id: 0,
        descNaturePlan: 'AAAAAAA',
        typeNaturePlan: TypeNaturePlan.R,
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

      it('should create a NaturePlan', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new NaturePlan()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a NaturePlan', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            descNaturePlan: 'BBBBBB',
            typeNaturePlan: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a NaturePlan', () => {
        const patchObject = Object.assign(
          {
            typeNaturePlan: 'BBBBBB',
          },
          new NaturePlan()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of NaturePlan', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            descNaturePlan: 'BBBBBB',
            typeNaturePlan: 'BBBBBB',
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

      it('should delete a NaturePlan', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addNaturePlanToCollectionIfMissing', () => {
        it('should add a NaturePlan to an empty array', () => {
          const naturePlan: INaturePlan = { id: 123 };
          expectedResult = service.addNaturePlanToCollectionIfMissing([], naturePlan);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(naturePlan);
        });

        it('should not add a NaturePlan to an array that contains it', () => {
          const naturePlan: INaturePlan = { id: 123 };
          const naturePlanCollection: INaturePlan[] = [
            {
              ...naturePlan,
            },
            { id: 456 },
          ];
          expectedResult = service.addNaturePlanToCollectionIfMissing(naturePlanCollection, naturePlan);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a NaturePlan to an array that doesn't contain it", () => {
          const naturePlan: INaturePlan = { id: 123 };
          const naturePlanCollection: INaturePlan[] = [{ id: 456 }];
          expectedResult = service.addNaturePlanToCollectionIfMissing(naturePlanCollection, naturePlan);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(naturePlan);
        });

        it('should add only unique NaturePlan to an array', () => {
          const naturePlanArray: INaturePlan[] = [{ id: 123 }, { id: 456 }, { id: 14145 }];
          const naturePlanCollection: INaturePlan[] = [{ id: 123 }];
          expectedResult = service.addNaturePlanToCollectionIfMissing(naturePlanCollection, ...naturePlanArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const naturePlan: INaturePlan = { id: 123 };
          const naturePlan2: INaturePlan = { id: 456 };
          expectedResult = service.addNaturePlanToCollectionIfMissing([], naturePlan, naturePlan2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(naturePlan);
          expect(expectedResult).toContain(naturePlan2);
        });

        it('should accept null and undefined values', () => {
          const naturePlan: INaturePlan = { id: 123 };
          expectedResult = service.addNaturePlanToCollectionIfMissing([], null, naturePlan, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(naturePlan);
        });

        it('should return initial array if no NaturePlan is added', () => {
          const naturePlanCollection: INaturePlan[] = [{ id: 123 }];
          expectedResult = service.addNaturePlanToCollectionIfMissing(naturePlanCollection, undefined, null);
          expect(expectedResult).toEqual(naturePlanCollection);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});

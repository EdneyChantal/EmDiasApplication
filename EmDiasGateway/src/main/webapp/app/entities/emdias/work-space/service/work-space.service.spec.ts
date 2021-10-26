import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IWorkSpace, WorkSpace } from '../work-space.model';

import { WorkSpaceService } from './work-space.service';

describe('Service Tests', () => {
  describe('WorkSpace Service', () => {
    let service: WorkSpaceService;
    let httpMock: HttpTestingController;
    let elemDefault: IWorkSpace;
    let expectedResult: IWorkSpace | IWorkSpace[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(WorkSpaceService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        id: 0,
        nome: 'AAAAAAA',
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

      it('should create a WorkSpace', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new WorkSpace()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a WorkSpace', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            nome: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a WorkSpace', () => {
        const patchObject = Object.assign(
          {
            nome: 'BBBBBB',
          },
          new WorkSpace()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of WorkSpace', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            nome: 'BBBBBB',
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

      it('should delete a WorkSpace', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addWorkSpaceToCollectionIfMissing', () => {
        it('should add a WorkSpace to an empty array', () => {
          const workSpace: IWorkSpace = { id: 123 };
          expectedResult = service.addWorkSpaceToCollectionIfMissing([], workSpace);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(workSpace);
        });

        it('should not add a WorkSpace to an array that contains it', () => {
          const workSpace: IWorkSpace = { id: 123 };
          const workSpaceCollection: IWorkSpace[] = [
            {
              ...workSpace,
            },
            { id: 456 },
          ];
          expectedResult = service.addWorkSpaceToCollectionIfMissing(workSpaceCollection, workSpace);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a WorkSpace to an array that doesn't contain it", () => {
          const workSpace: IWorkSpace = { id: 123 };
          const workSpaceCollection: IWorkSpace[] = [{ id: 456 }];
          expectedResult = service.addWorkSpaceToCollectionIfMissing(workSpaceCollection, workSpace);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(workSpace);
        });

        it('should add only unique WorkSpace to an array', () => {
          const workSpaceArray: IWorkSpace[] = [{ id: 123 }, { id: 456 }, { id: 26703 }];
          const workSpaceCollection: IWorkSpace[] = [{ id: 123 }];
          expectedResult = service.addWorkSpaceToCollectionIfMissing(workSpaceCollection, ...workSpaceArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const workSpace: IWorkSpace = { id: 123 };
          const workSpace2: IWorkSpace = { id: 456 };
          expectedResult = service.addWorkSpaceToCollectionIfMissing([], workSpace, workSpace2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(workSpace);
          expect(expectedResult).toContain(workSpace2);
        });

        it('should accept null and undefined values', () => {
          const workSpace: IWorkSpace = { id: 123 };
          expectedResult = service.addWorkSpaceToCollectionIfMissing([], null, workSpace, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(workSpace);
        });

        it('should return initial array if no WorkSpace is added', () => {
          const workSpaceCollection: IWorkSpace[] = [{ id: 123 }];
          expectedResult = service.addWorkSpaceToCollectionIfMissing(workSpaceCollection, undefined, null);
          expect(expectedResult).toEqual(workSpaceCollection);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});

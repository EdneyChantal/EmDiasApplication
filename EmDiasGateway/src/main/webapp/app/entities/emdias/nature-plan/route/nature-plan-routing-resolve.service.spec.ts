jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { INaturePlan, NaturePlan } from '../nature-plan.model';
import { NaturePlanService } from '../service/nature-plan.service';

import { NaturePlanRoutingResolveService } from './nature-plan-routing-resolve.service';

describe('Service Tests', () => {
  describe('NaturePlan routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: NaturePlanRoutingResolveService;
    let service: NaturePlanService;
    let resultNaturePlan: INaturePlan | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(NaturePlanRoutingResolveService);
      service = TestBed.inject(NaturePlanService);
      resultNaturePlan = undefined;
    });

    describe('resolve', () => {
      it('should return INaturePlan returned by find', () => {
        // GIVEN
        service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultNaturePlan = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultNaturePlan).toEqual({ id: 123 });
      });

      it('should return new INaturePlan if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultNaturePlan = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultNaturePlan).toEqual(new NaturePlan());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse({ body: null as unknown as NaturePlan })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultNaturePlan = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultNaturePlan).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});

jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { IAccountBank, AccountBank } from '../account-bank.model';
import { AccountBankService } from '../service/account-bank.service';

import { AccountBankRoutingResolveService } from './account-bank-routing-resolve.service';

describe('Service Tests', () => {
  describe('AccountBank routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: AccountBankRoutingResolveService;
    let service: AccountBankService;
    let resultAccountBank: IAccountBank | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(AccountBankRoutingResolveService);
      service = TestBed.inject(AccountBankService);
      resultAccountBank = undefined;
    });

    describe('resolve', () => {
      it('should return IAccountBank returned by find', () => {
        // GIVEN
        service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultAccountBank = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultAccountBank).toEqual({ id: 123 });
      });

      it('should return new IAccountBank if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultAccountBank = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultAccountBank).toEqual(new AccountBank());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse({ body: null as unknown as AccountBank })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultAccountBank = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultAccountBank).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});

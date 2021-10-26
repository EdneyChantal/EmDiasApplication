import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { AccountBankService } from '../service/account-bank.service';

import { AccountBankComponent } from './account-bank.component';

describe('Component Tests', () => {
  describe('AccountBank Management Component', () => {
    let comp: AccountBankComponent;
    let fixture: ComponentFixture<AccountBankComponent>;
    let service: AccountBankService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [AccountBankComponent],
      })
        .overrideTemplate(AccountBankComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AccountBankComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(AccountBankService);

      const headers = new HttpHeaders().append('link', 'link;link');
      jest.spyOn(service, 'query').mockReturnValue(
        of(
          new HttpResponse({
            body: [{ id: 123 }],
            headers,
          })
        )
      );
    });

    it('Should call load all on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.accountBanks?.[0]).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});

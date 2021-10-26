import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AccountBankDetailComponent } from './account-bank-detail.component';

describe('Component Tests', () => {
  describe('AccountBank Management Detail Component', () => {
    let comp: AccountBankDetailComponent;
    let fixture: ComponentFixture<AccountBankDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [AccountBankDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ accountBank: { id: 123 } }) },
          },
        ],
      })
        .overrideTemplate(AccountBankDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AccountBankDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load accountBank on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.accountBank).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
  });
});

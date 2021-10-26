import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { MovementBankService } from '../service/movement-bank.service';

import { MovementBankComponent } from './movement-bank.component';

describe('Component Tests', () => {
  describe('MovementBank Management Component', () => {
    let comp: MovementBankComponent;
    let fixture: ComponentFixture<MovementBankComponent>;
    let service: MovementBankService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [MovementBankComponent],
      })
        .overrideTemplate(MovementBankComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MovementBankComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(MovementBankService);

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
      expect(comp.movementBanks?.[0]).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { WorkSpaceService } from '../service/work-space.service';

import { WorkSpaceComponent } from './work-space.component';

describe('Component Tests', () => {
  describe('WorkSpace Management Component', () => {
    let comp: WorkSpaceComponent;
    let fixture: ComponentFixture<WorkSpaceComponent>;
    let service: WorkSpaceService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [WorkSpaceComponent],
      })
        .overrideTemplate(WorkSpaceComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WorkSpaceComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(WorkSpaceService);

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
      expect(comp.workSpaces?.[0]).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});

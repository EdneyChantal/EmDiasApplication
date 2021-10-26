import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { WorkSpaceDetailComponent } from './work-space-detail.component';

describe('Component Tests', () => {
  describe('WorkSpace Management Detail Component', () => {
    let comp: WorkSpaceDetailComponent;
    let fixture: ComponentFixture<WorkSpaceDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [WorkSpaceDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ workSpace: { id: 123 } }) },
          },
        ],
      })
        .overrideTemplate(WorkSpaceDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(WorkSpaceDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load workSpace on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.workSpace).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
  });
});

import { TestBed } from '@angular/core/testing';

import { EmdiasService } from './emdias.service';

describe('EmdiasService', () => {
  let service: EmdiasService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EmdiasService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

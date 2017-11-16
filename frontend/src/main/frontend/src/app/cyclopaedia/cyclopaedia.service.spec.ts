import { TestBed, inject } from '@angular/core/testing';

import { CyclopaediaService } from './cyclopaedia.service';

describe('CyclopaediaService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CyclopaediaService]
    });
  });

  it('should be created', inject([CyclopaediaService], (service: CyclopaediaService) => {
    expect(service).toBeTruthy();
  }));
});

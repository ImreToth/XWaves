import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CyclopaediaComponent } from './cyclopaedia.component';

describe('CyclopaediaComponent', () => {
  let component: CyclopaediaComponent;
  let fixture: ComponentFixture<CyclopaediaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CyclopaediaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CyclopaediaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

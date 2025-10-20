import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Asgignar } from './asgignar';

describe('Asgignar', () => {
  let component: Asgignar;
  let fixture: ComponentFixture<Asgignar>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Asgignar]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Asgignar);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

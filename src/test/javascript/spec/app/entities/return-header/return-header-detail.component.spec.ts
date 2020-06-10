import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { ReturnHeaderDetailComponent } from 'app/entities/return-header/return-header-detail.component';
import { ReturnHeader } from 'app/shared/model/return-header.model';

describe('Component Tests', () => {
  describe('ReturnHeader Management Detail Component', () => {
    let comp: ReturnHeaderDetailComponent;
    let fixture: ComponentFixture<ReturnHeaderDetailComponent>;
    const route = ({ data: of({ returnHeader: new ReturnHeader(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [ReturnHeaderDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ReturnHeaderDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ReturnHeaderDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load returnHeader on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.returnHeader).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});

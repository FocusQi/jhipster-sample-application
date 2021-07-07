import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { PriceCompareDetailComponent } from 'app/entities/price-compare/price-compare-detail.component';
import { PriceCompare } from 'app/shared/model/price-compare.model';

describe('Component Tests', () => {
  describe('PriceCompare Management Detail Component', () => {
    let comp: PriceCompareDetailComponent;
    let fixture: ComponentFixture<PriceCompareDetailComponent>;
    const route = ({ data: of({ priceCompare: new PriceCompare(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [PriceCompareDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(PriceCompareDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PriceCompareDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load priceCompare on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.priceCompare).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});

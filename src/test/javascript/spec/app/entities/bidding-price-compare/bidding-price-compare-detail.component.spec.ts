import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { BiddingPriceCompareDetailComponent } from 'app/entities/bidding-price-compare/bidding-price-compare-detail.component';
import { BiddingPriceCompare } from 'app/shared/model/bidding-price-compare.model';

describe('Component Tests', () => {
  describe('BiddingPriceCompare Management Detail Component', () => {
    let comp: BiddingPriceCompareDetailComponent;
    let fixture: ComponentFixture<BiddingPriceCompareDetailComponent>;
    const route = ({ data: of({ biddingPriceCompare: new BiddingPriceCompare(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [BiddingPriceCompareDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(BiddingPriceCompareDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BiddingPriceCompareDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load biddingPriceCompare on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.biddingPriceCompare).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { BiddingQuotationHeaderDetailComponent } from 'app/entities/bidding-quotation-header/bidding-quotation-header-detail.component';
import { BiddingQuotationHeader } from 'app/shared/model/bidding-quotation-header.model';

describe('Component Tests', () => {
  describe('BiddingQuotationHeader Management Detail Component', () => {
    let comp: BiddingQuotationHeaderDetailComponent;
    let fixture: ComponentFixture<BiddingQuotationHeaderDetailComponent>;
    const route = ({ data: of({ biddingQuotationHeader: new BiddingQuotationHeader(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [BiddingQuotationHeaderDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(BiddingQuotationHeaderDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BiddingQuotationHeaderDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load biddingQuotationHeader on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.biddingQuotationHeader).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});

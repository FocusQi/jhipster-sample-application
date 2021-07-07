import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { BiddingQuotationInfoDetailComponent } from 'app/entities/bidding-quotation-info/bidding-quotation-info-detail.component';
import { BiddingQuotationInfo } from 'app/shared/model/bidding-quotation-info.model';

describe('Component Tests', () => {
  describe('BiddingQuotationInfo Management Detail Component', () => {
    let comp: BiddingQuotationInfoDetailComponent;
    let fixture: ComponentFixture<BiddingQuotationInfoDetailComponent>;
    const route = ({ data: of({ biddingQuotationInfo: new BiddingQuotationInfo(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [BiddingQuotationInfoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(BiddingQuotationInfoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BiddingQuotationInfoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load biddingQuotationInfo on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.biddingQuotationInfo).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});

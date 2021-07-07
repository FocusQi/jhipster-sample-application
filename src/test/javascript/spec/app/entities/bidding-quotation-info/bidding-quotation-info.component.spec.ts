import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { BiddingQuotationInfoComponent } from 'app/entities/bidding-quotation-info/bidding-quotation-info.component';
import { BiddingQuotationInfoService } from 'app/entities/bidding-quotation-info/bidding-quotation-info.service';
import { BiddingQuotationInfo } from 'app/shared/model/bidding-quotation-info.model';

describe('Component Tests', () => {
  describe('BiddingQuotationInfo Management Component', () => {
    let comp: BiddingQuotationInfoComponent;
    let fixture: ComponentFixture<BiddingQuotationInfoComponent>;
    let service: BiddingQuotationInfoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [BiddingQuotationInfoComponent],
      })
        .overrideTemplate(BiddingQuotationInfoComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BiddingQuotationInfoComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BiddingQuotationInfoService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new BiddingQuotationInfo(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.biddingQuotationInfos && comp.biddingQuotationInfos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});

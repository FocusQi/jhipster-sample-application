import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { BiddingQuotationHeaderComponent } from 'app/entities/bidding-quotation-header/bidding-quotation-header.component';
import { BiddingQuotationHeaderService } from 'app/entities/bidding-quotation-header/bidding-quotation-header.service';
import { BiddingQuotationHeader } from 'app/shared/model/bidding-quotation-header.model';

describe('Component Tests', () => {
  describe('BiddingQuotationHeader Management Component', () => {
    let comp: BiddingQuotationHeaderComponent;
    let fixture: ComponentFixture<BiddingQuotationHeaderComponent>;
    let service: BiddingQuotationHeaderService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [BiddingQuotationHeaderComponent],
      })
        .overrideTemplate(BiddingQuotationHeaderComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BiddingQuotationHeaderComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BiddingQuotationHeaderService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new BiddingQuotationHeader(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.biddingQuotationHeaders && comp.biddingQuotationHeaders[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});

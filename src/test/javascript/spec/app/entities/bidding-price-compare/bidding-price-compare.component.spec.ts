import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { BiddingPriceCompareComponent } from 'app/entities/bidding-price-compare/bidding-price-compare.component';
import { BiddingPriceCompareService } from 'app/entities/bidding-price-compare/bidding-price-compare.service';
import { BiddingPriceCompare } from 'app/shared/model/bidding-price-compare.model';

describe('Component Tests', () => {
  describe('BiddingPriceCompare Management Component', () => {
    let comp: BiddingPriceCompareComponent;
    let fixture: ComponentFixture<BiddingPriceCompareComponent>;
    let service: BiddingPriceCompareService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [BiddingPriceCompareComponent],
      })
        .overrideTemplate(BiddingPriceCompareComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BiddingPriceCompareComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BiddingPriceCompareService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new BiddingPriceCompare(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.biddingPriceCompares && comp.biddingPriceCompares[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});

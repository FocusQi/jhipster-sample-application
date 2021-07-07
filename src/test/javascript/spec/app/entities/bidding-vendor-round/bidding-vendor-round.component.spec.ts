import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { BiddingVendorRoundComponent } from 'app/entities/bidding-vendor-round/bidding-vendor-round.component';
import { BiddingVendorRoundService } from 'app/entities/bidding-vendor-round/bidding-vendor-round.service';
import { BiddingVendorRound } from 'app/shared/model/bidding-vendor-round.model';

describe('Component Tests', () => {
  describe('BiddingVendorRound Management Component', () => {
    let comp: BiddingVendorRoundComponent;
    let fixture: ComponentFixture<BiddingVendorRoundComponent>;
    let service: BiddingVendorRoundService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [BiddingVendorRoundComponent],
      })
        .overrideTemplate(BiddingVendorRoundComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BiddingVendorRoundComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BiddingVendorRoundService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new BiddingVendorRound(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.biddingVendorRounds && comp.biddingVendorRounds[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});

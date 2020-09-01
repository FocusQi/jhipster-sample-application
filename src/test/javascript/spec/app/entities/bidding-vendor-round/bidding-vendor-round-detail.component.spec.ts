import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { BiddingVendorRoundDetailComponent } from 'app/entities/bidding-vendor-round/bidding-vendor-round-detail.component';
import { BiddingVendorRound } from 'app/shared/model/bidding-vendor-round.model';

describe('Component Tests', () => {
  describe('BiddingVendorRound Management Detail Component', () => {
    let comp: BiddingVendorRoundDetailComponent;
    let fixture: ComponentFixture<BiddingVendorRoundDetailComponent>;
    const route = ({ data: of({ biddingVendorRound: new BiddingVendorRound(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [BiddingVendorRoundDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(BiddingVendorRoundDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BiddingVendorRoundDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load biddingVendorRound on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.biddingVendorRound).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});

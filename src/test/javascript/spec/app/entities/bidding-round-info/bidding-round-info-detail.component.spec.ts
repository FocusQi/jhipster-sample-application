import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { BiddingRoundInfoDetailComponent } from 'app/entities/bidding-round-info/bidding-round-info-detail.component';
import { BiddingRoundInfo } from 'app/shared/model/bidding-round-info.model';

describe('Component Tests', () => {
  describe('BiddingRoundInfo Management Detail Component', () => {
    let comp: BiddingRoundInfoDetailComponent;
    let fixture: ComponentFixture<BiddingRoundInfoDetailComponent>;
    const route = ({ data: of({ biddingRoundInfo: new BiddingRoundInfo(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [BiddingRoundInfoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(BiddingRoundInfoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BiddingRoundInfoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load biddingRoundInfo on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.biddingRoundInfo).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});

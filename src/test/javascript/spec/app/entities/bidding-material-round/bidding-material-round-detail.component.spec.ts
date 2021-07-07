import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { BiddingMaterialRoundDetailComponent } from 'app/entities/bidding-material-round/bidding-material-round-detail.component';
import { BiddingMaterialRound } from 'app/shared/model/bidding-material-round.model';

describe('Component Tests', () => {
  describe('BiddingMaterialRound Management Detail Component', () => {
    let comp: BiddingMaterialRoundDetailComponent;
    let fixture: ComponentFixture<BiddingMaterialRoundDetailComponent>;
    const route = ({ data: of({ biddingMaterialRound: new BiddingMaterialRound(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [BiddingMaterialRoundDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(BiddingMaterialRoundDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BiddingMaterialRoundDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load biddingMaterialRound on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.biddingMaterialRound).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});

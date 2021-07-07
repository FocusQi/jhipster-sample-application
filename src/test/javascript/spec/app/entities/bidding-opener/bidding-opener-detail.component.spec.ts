import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { BiddingOpenerDetailComponent } from 'app/entities/bidding-opener/bidding-opener-detail.component';
import { BiddingOpener } from 'app/shared/model/bidding-opener.model';

describe('Component Tests', () => {
  describe('BiddingOpener Management Detail Component', () => {
    let comp: BiddingOpenerDetailComponent;
    let fixture: ComponentFixture<BiddingOpenerDetailComponent>;
    const route = ({ data: of({ biddingOpener: new BiddingOpener(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [BiddingOpenerDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(BiddingOpenerDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BiddingOpenerDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load biddingOpener on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.biddingOpener).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});

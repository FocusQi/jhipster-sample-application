import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { BiddingRoundInfoComponent } from 'app/entities/bidding-round-info/bidding-round-info.component';
import { BiddingRoundInfoService } from 'app/entities/bidding-round-info/bidding-round-info.service';
import { BiddingRoundInfo } from 'app/shared/model/bidding-round-info.model';

describe('Component Tests', () => {
  describe('BiddingRoundInfo Management Component', () => {
    let comp: BiddingRoundInfoComponent;
    let fixture: ComponentFixture<BiddingRoundInfoComponent>;
    let service: BiddingRoundInfoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [BiddingRoundInfoComponent],
      })
        .overrideTemplate(BiddingRoundInfoComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BiddingRoundInfoComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BiddingRoundInfoService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new BiddingRoundInfo(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.biddingRoundInfos && comp.biddingRoundInfos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});

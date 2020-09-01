import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { BiddingOpenerComponent } from 'app/entities/bidding-opener/bidding-opener.component';
import { BiddingOpenerService } from 'app/entities/bidding-opener/bidding-opener.service';
import { BiddingOpener } from 'app/shared/model/bidding-opener.model';

describe('Component Tests', () => {
  describe('BiddingOpener Management Component', () => {
    let comp: BiddingOpenerComponent;
    let fixture: ComponentFixture<BiddingOpenerComponent>;
    let service: BiddingOpenerService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [BiddingOpenerComponent],
      })
        .overrideTemplate(BiddingOpenerComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BiddingOpenerComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BiddingOpenerService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new BiddingOpener(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.biddingOpeners && comp.biddingOpeners[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});

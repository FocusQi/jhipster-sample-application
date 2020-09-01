import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { BiddingMaterialRoundComponent } from 'app/entities/bidding-material-round/bidding-material-round.component';
import { BiddingMaterialRoundService } from 'app/entities/bidding-material-round/bidding-material-round.service';
import { BiddingMaterialRound } from 'app/shared/model/bidding-material-round.model';

describe('Component Tests', () => {
  describe('BiddingMaterialRound Management Component', () => {
    let comp: BiddingMaterialRoundComponent;
    let fixture: ComponentFixture<BiddingMaterialRoundComponent>;
    let service: BiddingMaterialRoundService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [BiddingMaterialRoundComponent],
      })
        .overrideTemplate(BiddingMaterialRoundComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BiddingMaterialRoundComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BiddingMaterialRoundService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new BiddingMaterialRound(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.biddingMaterialRounds && comp.biddingMaterialRounds[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});

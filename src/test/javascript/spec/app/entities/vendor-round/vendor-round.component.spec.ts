import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { VendorRoundComponent } from 'app/entities/vendor-round/vendor-round.component';
import { VendorRoundService } from 'app/entities/vendor-round/vendor-round.service';
import { VendorRound } from 'app/shared/model/vendor-round.model';

describe('Component Tests', () => {
  describe('VendorRound Management Component', () => {
    let comp: VendorRoundComponent;
    let fixture: ComponentFixture<VendorRoundComponent>;
    let service: VendorRoundService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [VendorRoundComponent],
      })
        .overrideTemplate(VendorRoundComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(VendorRoundComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(VendorRoundService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new VendorRound(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.vendorRounds && comp.vendorRounds[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});

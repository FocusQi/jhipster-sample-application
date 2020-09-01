import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { PriceCompareComponent } from 'app/entities/price-compare/price-compare.component';
import { PriceCompareService } from 'app/entities/price-compare/price-compare.service';
import { PriceCompare } from 'app/shared/model/price-compare.model';

describe('Component Tests', () => {
  describe('PriceCompare Management Component', () => {
    let comp: PriceCompareComponent;
    let fixture: ComponentFixture<PriceCompareComponent>;
    let service: PriceCompareService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [PriceCompareComponent],
      })
        .overrideTemplate(PriceCompareComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PriceCompareComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PriceCompareService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new PriceCompare(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.priceCompares && comp.priceCompares[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});

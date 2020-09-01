import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { QuotationInfoComponent } from 'app/entities/quotation-info/quotation-info.component';
import { QuotationInfoService } from 'app/entities/quotation-info/quotation-info.service';
import { QuotationInfo } from 'app/shared/model/quotation-info.model';

describe('Component Tests', () => {
  describe('QuotationInfo Management Component', () => {
    let comp: QuotationInfoComponent;
    let fixture: ComponentFixture<QuotationInfoComponent>;
    let service: QuotationInfoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [QuotationInfoComponent],
      })
        .overrideTemplate(QuotationInfoComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(QuotationInfoComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(QuotationInfoService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new QuotationInfo(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.quotationInfos && comp.quotationInfos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});

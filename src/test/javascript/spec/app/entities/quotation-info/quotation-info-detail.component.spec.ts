import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { QuotationInfoDetailComponent } from 'app/entities/quotation-info/quotation-info-detail.component';
import { QuotationInfo } from 'app/shared/model/quotation-info.model';

describe('Component Tests', () => {
  describe('QuotationInfo Management Detail Component', () => {
    let comp: QuotationInfoDetailComponent;
    let fixture: ComponentFixture<QuotationInfoDetailComponent>;
    const route = ({ data: of({ quotationInfo: new QuotationInfo(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [QuotationInfoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(QuotationInfoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(QuotationInfoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load quotationInfo on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.quotationInfo).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});

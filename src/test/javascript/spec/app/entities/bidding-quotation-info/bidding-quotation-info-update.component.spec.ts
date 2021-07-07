import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { BiddingQuotationInfoUpdateComponent } from 'app/entities/bidding-quotation-info/bidding-quotation-info-update.component';
import { BiddingQuotationInfoService } from 'app/entities/bidding-quotation-info/bidding-quotation-info.service';
import { BiddingQuotationInfo } from 'app/shared/model/bidding-quotation-info.model';

describe('Component Tests', () => {
  describe('BiddingQuotationInfo Management Update Component', () => {
    let comp: BiddingQuotationInfoUpdateComponent;
    let fixture: ComponentFixture<BiddingQuotationInfoUpdateComponent>;
    let service: BiddingQuotationInfoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [BiddingQuotationInfoUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(BiddingQuotationInfoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BiddingQuotationInfoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BiddingQuotationInfoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new BiddingQuotationInfo(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new BiddingQuotationInfo();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});

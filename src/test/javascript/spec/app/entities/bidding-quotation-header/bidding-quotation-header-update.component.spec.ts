import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { BiddingQuotationHeaderUpdateComponent } from 'app/entities/bidding-quotation-header/bidding-quotation-header-update.component';
import { BiddingQuotationHeaderService } from 'app/entities/bidding-quotation-header/bidding-quotation-header.service';
import { BiddingQuotationHeader } from 'app/shared/model/bidding-quotation-header.model';

describe('Component Tests', () => {
  describe('BiddingQuotationHeader Management Update Component', () => {
    let comp: BiddingQuotationHeaderUpdateComponent;
    let fixture: ComponentFixture<BiddingQuotationHeaderUpdateComponent>;
    let service: BiddingQuotationHeaderService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [BiddingQuotationHeaderUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(BiddingQuotationHeaderUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BiddingQuotationHeaderUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BiddingQuotationHeaderService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new BiddingQuotationHeader(123);
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
        const entity = new BiddingQuotationHeader();
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

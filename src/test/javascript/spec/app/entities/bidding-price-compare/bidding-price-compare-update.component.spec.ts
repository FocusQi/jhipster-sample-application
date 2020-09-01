import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { BiddingPriceCompareUpdateComponent } from 'app/entities/bidding-price-compare/bidding-price-compare-update.component';
import { BiddingPriceCompareService } from 'app/entities/bidding-price-compare/bidding-price-compare.service';
import { BiddingPriceCompare } from 'app/shared/model/bidding-price-compare.model';

describe('Component Tests', () => {
  describe('BiddingPriceCompare Management Update Component', () => {
    let comp: BiddingPriceCompareUpdateComponent;
    let fixture: ComponentFixture<BiddingPriceCompareUpdateComponent>;
    let service: BiddingPriceCompareService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [BiddingPriceCompareUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(BiddingPriceCompareUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BiddingPriceCompareUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BiddingPriceCompareService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new BiddingPriceCompare(123);
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
        const entity = new BiddingPriceCompare();
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

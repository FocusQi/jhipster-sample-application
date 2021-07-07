import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { PriceCompareUpdateComponent } from 'app/entities/price-compare/price-compare-update.component';
import { PriceCompareService } from 'app/entities/price-compare/price-compare.service';
import { PriceCompare } from 'app/shared/model/price-compare.model';

describe('Component Tests', () => {
  describe('PriceCompare Management Update Component', () => {
    let comp: PriceCompareUpdateComponent;
    let fixture: ComponentFixture<PriceCompareUpdateComponent>;
    let service: PriceCompareService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [PriceCompareUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(PriceCompareUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PriceCompareUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PriceCompareService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new PriceCompare(123);
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
        const entity = new PriceCompare();
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

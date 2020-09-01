import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { VendorRoundUpdateComponent } from 'app/entities/vendor-round/vendor-round-update.component';
import { VendorRoundService } from 'app/entities/vendor-round/vendor-round.service';
import { VendorRound } from 'app/shared/model/vendor-round.model';

describe('Component Tests', () => {
  describe('VendorRound Management Update Component', () => {
    let comp: VendorRoundUpdateComponent;
    let fixture: ComponentFixture<VendorRoundUpdateComponent>;
    let service: VendorRoundService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [VendorRoundUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(VendorRoundUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(VendorRoundUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(VendorRoundService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new VendorRound(123);
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
        const entity = new VendorRound();
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

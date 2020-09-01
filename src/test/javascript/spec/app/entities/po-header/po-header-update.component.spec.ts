import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { PoHeaderUpdateComponent } from 'app/entities/po-header/po-header-update.component';
import { PoHeaderService } from 'app/entities/po-header/po-header.service';
import { PoHeader } from 'app/shared/model/po-header.model';

describe('Component Tests', () => {
  describe('PoHeader Management Update Component', () => {
    let comp: PoHeaderUpdateComponent;
    let fixture: ComponentFixture<PoHeaderUpdateComponent>;
    let service: PoHeaderService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [PoHeaderUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(PoHeaderUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PoHeaderUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PoHeaderService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new PoHeader(123);
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
        const entity = new PoHeader();
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

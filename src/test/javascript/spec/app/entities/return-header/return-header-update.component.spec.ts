import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { ReturnHeaderUpdateComponent } from 'app/entities/return-header/return-header-update.component';
import { ReturnHeaderService } from 'app/entities/return-header/return-header.service';
import { ReturnHeader } from 'app/shared/model/return-header.model';

describe('Component Tests', () => {
  describe('ReturnHeader Management Update Component', () => {
    let comp: ReturnHeaderUpdateComponent;
    let fixture: ComponentFixture<ReturnHeaderUpdateComponent>;
    let service: ReturnHeaderService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [ReturnHeaderUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ReturnHeaderUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ReturnHeaderUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ReturnHeaderService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ReturnHeader(123);
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
        const entity = new ReturnHeader();
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

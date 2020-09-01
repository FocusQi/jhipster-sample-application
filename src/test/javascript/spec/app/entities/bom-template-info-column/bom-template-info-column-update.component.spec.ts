import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { BomTemplateInfoColumnUpdateComponent } from 'app/entities/bom-template-info-column/bom-template-info-column-update.component';
import { BomTemplateInfoColumnService } from 'app/entities/bom-template-info-column/bom-template-info-column.service';
import { BomTemplateInfoColumn } from 'app/shared/model/bom-template-info-column.model';

describe('Component Tests', () => {
  describe('BomTemplateInfoColumn Management Update Component', () => {
    let comp: BomTemplateInfoColumnUpdateComponent;
    let fixture: ComponentFixture<BomTemplateInfoColumnUpdateComponent>;
    let service: BomTemplateInfoColumnService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [BomTemplateInfoColumnUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(BomTemplateInfoColumnUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BomTemplateInfoColumnUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BomTemplateInfoColumnService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new BomTemplateInfoColumn(123);
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
        const entity = new BomTemplateInfoColumn();
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

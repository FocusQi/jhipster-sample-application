import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { BomTemplateInfoUpdateComponent } from 'app/entities/bom-template-info/bom-template-info-update.component';
import { BomTemplateInfoService } from 'app/entities/bom-template-info/bom-template-info.service';
import { BomTemplateInfo } from 'app/shared/model/bom-template-info.model';

describe('Component Tests', () => {
  describe('BomTemplateInfo Management Update Component', () => {
    let comp: BomTemplateInfoUpdateComponent;
    let fixture: ComponentFixture<BomTemplateInfoUpdateComponent>;
    let service: BomTemplateInfoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [BomTemplateInfoUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(BomTemplateInfoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BomTemplateInfoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BomTemplateInfoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new BomTemplateInfo(123);
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
        const entity = new BomTemplateInfo();
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

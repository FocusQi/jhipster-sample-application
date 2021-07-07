import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { OrgUpdateComponent } from 'app/entities/org/org-update.component';
import { OrgService } from 'app/entities/org/org.service';
import { Org } from 'app/shared/model/org.model';

describe('Component Tests', () => {
  describe('Org Management Update Component', () => {
    let comp: OrgUpdateComponent;
    let fixture: ComponentFixture<OrgUpdateComponent>;
    let service: OrgService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [OrgUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(OrgUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OrgUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OrgService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Org(123);
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
        const entity = new Org();
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

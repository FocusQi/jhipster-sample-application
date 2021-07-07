import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { MaterialRoundUpdateComponent } from 'app/entities/material-round/material-round-update.component';
import { MaterialRoundService } from 'app/entities/material-round/material-round.service';
import { MaterialRound } from 'app/shared/model/material-round.model';

describe('Component Tests', () => {
  describe('MaterialRound Management Update Component', () => {
    let comp: MaterialRoundUpdateComponent;
    let fixture: ComponentFixture<MaterialRoundUpdateComponent>;
    let service: MaterialRoundService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [MaterialRoundUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(MaterialRoundUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MaterialRoundUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MaterialRoundService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MaterialRound(123);
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
        const entity = new MaterialRound();
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

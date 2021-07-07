import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { SrmMsgListUpdateComponent } from 'app/entities/srm-msg-list/srm-msg-list-update.component';
import { SrmMsgListService } from 'app/entities/srm-msg-list/srm-msg-list.service';
import { SrmMsgList } from 'app/shared/model/srm-msg-list.model';

describe('Component Tests', () => {
  describe('SrmMsgList Management Update Component', () => {
    let comp: SrmMsgListUpdateComponent;
    let fixture: ComponentFixture<SrmMsgListUpdateComponent>;
    let service: SrmMsgListService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [SrmMsgListUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(SrmMsgListUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SrmMsgListUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SrmMsgListService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SrmMsgList(123);
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
        const entity = new SrmMsgList();
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

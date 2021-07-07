import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { BomTemplateHeaderDeleteDialogComponent } from 'app/entities/bom-template-header/bom-template-header-delete-dialog.component';
import { BomTemplateHeaderService } from 'app/entities/bom-template-header/bom-template-header.service';

describe('Component Tests', () => {
  describe('BomTemplateHeader Management Delete Component', () => {
    let comp: BomTemplateHeaderDeleteDialogComponent;
    let fixture: ComponentFixture<BomTemplateHeaderDeleteDialogComponent>;
    let service: BomTemplateHeaderService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [BomTemplateHeaderDeleteDialogComponent],
      })
        .overrideTemplate(BomTemplateHeaderDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BomTemplateHeaderDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BomTemplateHeaderService);
      mockEventManager = TestBed.get(JhiEventManager);
      mockActiveModal = TestBed.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.closeSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));

      it('Should not call delete service on clear', () => {
        // GIVEN
        spyOn(service, 'delete');

        // WHEN
        comp.cancel();

        // THEN
        expect(service.delete).not.toHaveBeenCalled();
        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
      });
    });
  });
});

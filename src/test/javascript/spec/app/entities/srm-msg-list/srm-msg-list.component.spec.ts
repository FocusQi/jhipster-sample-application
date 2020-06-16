import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { SrmMsgListComponent } from 'app/entities/srm-msg-list/srm-msg-list.component';
import { SrmMsgListService } from 'app/entities/srm-msg-list/srm-msg-list.service';
import { SrmMsgList } from 'app/shared/model/srm-msg-list.model';

describe('Component Tests', () => {
  describe('SrmMsgList Management Component', () => {
    let comp: SrmMsgListComponent;
    let fixture: ComponentFixture<SrmMsgListComponent>;
    let service: SrmMsgListService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [SrmMsgListComponent],
      })
        .overrideTemplate(SrmMsgListComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SrmMsgListComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SrmMsgListService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new SrmMsgList(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.srmMsgLists && comp.srmMsgLists[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});

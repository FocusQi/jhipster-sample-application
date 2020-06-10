import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { ReturnHeaderComponent } from 'app/entities/return-header/return-header.component';
import { ReturnHeaderService } from 'app/entities/return-header/return-header.service';
import { ReturnHeader } from 'app/shared/model/return-header.model';

describe('Component Tests', () => {
  describe('ReturnHeader Management Component', () => {
    let comp: ReturnHeaderComponent;
    let fixture: ComponentFixture<ReturnHeaderComponent>;
    let service: ReturnHeaderService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [ReturnHeaderComponent],
      })
        .overrideTemplate(ReturnHeaderComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ReturnHeaderComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ReturnHeaderService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ReturnHeader(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.returnHeaders && comp.returnHeaders[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});

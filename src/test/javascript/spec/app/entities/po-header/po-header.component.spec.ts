import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { PoHeaderComponent } from 'app/entities/po-header/po-header.component';
import { PoHeaderService } from 'app/entities/po-header/po-header.service';
import { PoHeader } from 'app/shared/model/po-header.model';

describe('Component Tests', () => {
  describe('PoHeader Management Component', () => {
    let comp: PoHeaderComponent;
    let fixture: ComponentFixture<PoHeaderComponent>;
    let service: PoHeaderService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [PoHeaderComponent],
      })
        .overrideTemplate(PoHeaderComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PoHeaderComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PoHeaderService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new PoHeader(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.poHeaders && comp.poHeaders[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});

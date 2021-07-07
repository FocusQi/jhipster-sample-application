import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { BomTemplateInfoColumnComponent } from 'app/entities/bom-template-info-column/bom-template-info-column.component';
import { BomTemplateInfoColumnService } from 'app/entities/bom-template-info-column/bom-template-info-column.service';
import { BomTemplateInfoColumn } from 'app/shared/model/bom-template-info-column.model';

describe('Component Tests', () => {
  describe('BomTemplateInfoColumn Management Component', () => {
    let comp: BomTemplateInfoColumnComponent;
    let fixture: ComponentFixture<BomTemplateInfoColumnComponent>;
    let service: BomTemplateInfoColumnService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [BomTemplateInfoColumnComponent],
      })
        .overrideTemplate(BomTemplateInfoColumnComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BomTemplateInfoColumnComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BomTemplateInfoColumnService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new BomTemplateInfoColumn(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.bomTemplateInfoColumns && comp.bomTemplateInfoColumns[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { BomTemplateHeaderComponent } from 'app/entities/bom-template-header/bom-template-header.component';
import { BomTemplateHeaderService } from 'app/entities/bom-template-header/bom-template-header.service';
import { BomTemplateHeader } from 'app/shared/model/bom-template-header.model';

describe('Component Tests', () => {
  describe('BomTemplateHeader Management Component', () => {
    let comp: BomTemplateHeaderComponent;
    let fixture: ComponentFixture<BomTemplateHeaderComponent>;
    let service: BomTemplateHeaderService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [BomTemplateHeaderComponent],
      })
        .overrideTemplate(BomTemplateHeaderComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BomTemplateHeaderComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BomTemplateHeaderService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new BomTemplateHeader(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.bomTemplateHeaders && comp.bomTemplateHeaders[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { BomTemplateInfoComponent } from 'app/entities/bom-template-info/bom-template-info.component';
import { BomTemplateInfoService } from 'app/entities/bom-template-info/bom-template-info.service';
import { BomTemplateInfo } from 'app/shared/model/bom-template-info.model';

describe('Component Tests', () => {
  describe('BomTemplateInfo Management Component', () => {
    let comp: BomTemplateInfoComponent;
    let fixture: ComponentFixture<BomTemplateInfoComponent>;
    let service: BomTemplateInfoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [BomTemplateInfoComponent],
      })
        .overrideTemplate(BomTemplateInfoComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BomTemplateInfoComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BomTemplateInfoService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new BomTemplateInfo(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.bomTemplateInfos && comp.bomTemplateInfos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});

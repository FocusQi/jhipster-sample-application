import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { BomTemplateHeaderDetailComponent } from 'app/entities/bom-template-header/bom-template-header-detail.component';
import { BomTemplateHeader } from 'app/shared/model/bom-template-header.model';

describe('Component Tests', () => {
  describe('BomTemplateHeader Management Detail Component', () => {
    let comp: BomTemplateHeaderDetailComponent;
    let fixture: ComponentFixture<BomTemplateHeaderDetailComponent>;
    const route = ({ data: of({ bomTemplateHeader: new BomTemplateHeader(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [BomTemplateHeaderDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(BomTemplateHeaderDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BomTemplateHeaderDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load bomTemplateHeader on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.bomTemplateHeader).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});

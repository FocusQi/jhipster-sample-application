import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { BomTemplateInfoColumnDetailComponent } from 'app/entities/bom-template-info-column/bom-template-info-column-detail.component';
import { BomTemplateInfoColumn } from 'app/shared/model/bom-template-info-column.model';

describe('Component Tests', () => {
  describe('BomTemplateInfoColumn Management Detail Component', () => {
    let comp: BomTemplateInfoColumnDetailComponent;
    let fixture: ComponentFixture<BomTemplateInfoColumnDetailComponent>;
    const route = ({ data: of({ bomTemplateInfoColumn: new BomTemplateInfoColumn(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [BomTemplateInfoColumnDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(BomTemplateInfoColumnDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BomTemplateInfoColumnDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load bomTemplateInfoColumn on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.bomTemplateInfoColumn).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});

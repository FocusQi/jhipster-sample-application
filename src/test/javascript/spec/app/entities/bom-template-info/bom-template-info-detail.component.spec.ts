import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { BomTemplateInfoDetailComponent } from 'app/entities/bom-template-info/bom-template-info-detail.component';
import { BomTemplateInfo } from 'app/shared/model/bom-template-info.model';

describe('Component Tests', () => {
  describe('BomTemplateInfo Management Detail Component', () => {
    let comp: BomTemplateInfoDetailComponent;
    let fixture: ComponentFixture<BomTemplateInfoDetailComponent>;
    const route = ({ data: of({ bomTemplateInfo: new BomTemplateInfo(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [BomTemplateInfoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(BomTemplateInfoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BomTemplateInfoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load bomTemplateInfo on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.bomTemplateInfo).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});

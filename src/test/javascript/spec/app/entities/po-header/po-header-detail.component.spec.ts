import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { PoHeaderDetailComponent } from 'app/entities/po-header/po-header-detail.component';
import { PoHeader } from 'app/shared/model/po-header.model';

describe('Component Tests', () => {
  describe('PoHeader Management Detail Component', () => {
    let comp: PoHeaderDetailComponent;
    let fixture: ComponentFixture<PoHeaderDetailComponent>;
    const route = ({ data: of({ poHeader: new PoHeader(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [PoHeaderDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(PoHeaderDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PoHeaderDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load poHeader on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.poHeader).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});

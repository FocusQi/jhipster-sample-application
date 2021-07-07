import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { MaterialRoundDetailComponent } from 'app/entities/material-round/material-round-detail.component';
import { MaterialRound } from 'app/shared/model/material-round.model';

describe('Component Tests', () => {
  describe('MaterialRound Management Detail Component', () => {
    let comp: MaterialRoundDetailComponent;
    let fixture: ComponentFixture<MaterialRoundDetailComponent>;
    const route = ({ data: of({ materialRound: new MaterialRound(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [MaterialRoundDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(MaterialRoundDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MaterialRoundDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load materialRound on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.materialRound).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { OpenerDetailComponent } from 'app/entities/opener/opener-detail.component';
import { Opener } from 'app/shared/model/opener.model';

describe('Component Tests', () => {
  describe('Opener Management Detail Component', () => {
    let comp: OpenerDetailComponent;
    let fixture: ComponentFixture<OpenerDetailComponent>;
    const route = ({ data: of({ opener: new Opener(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [OpenerDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(OpenerDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(OpenerDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load opener on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.opener).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});

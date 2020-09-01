import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { VendorRoundDetailComponent } from 'app/entities/vendor-round/vendor-round-detail.component';
import { VendorRound } from 'app/shared/model/vendor-round.model';

describe('Component Tests', () => {
  describe('VendorRound Management Detail Component', () => {
    let comp: VendorRoundDetailComponent;
    let fixture: ComponentFixture<VendorRoundDetailComponent>;
    const route = ({ data: of({ vendorRound: new VendorRound(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [VendorRoundDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(VendorRoundDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(VendorRoundDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load vendorRound on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.vendorRound).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});

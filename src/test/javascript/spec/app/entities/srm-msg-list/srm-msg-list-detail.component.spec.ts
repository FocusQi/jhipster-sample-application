import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { SrmMsgListDetailComponent } from 'app/entities/srm-msg-list/srm-msg-list-detail.component';
import { SrmMsgList } from 'app/shared/model/srm-msg-list.model';

describe('Component Tests', () => {
  describe('SrmMsgList Management Detail Component', () => {
    let comp: SrmMsgListDetailComponent;
    let fixture: ComponentFixture<SrmMsgListDetailComponent>;
    const route = ({ data: of({ srmMsgList: new SrmMsgList(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [SrmMsgListDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(SrmMsgListDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SrmMsgListDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load srmMsgList on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.srmMsgList).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});

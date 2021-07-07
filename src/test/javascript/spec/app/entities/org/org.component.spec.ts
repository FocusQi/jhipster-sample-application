import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { OrgComponent } from 'app/entities/org/org.component';
import { OrgService } from 'app/entities/org/org.service';
import { Org } from 'app/shared/model/org.model';

describe('Component Tests', () => {
  describe('Org Management Component', () => {
    let comp: OrgComponent;
    let fixture: ComponentFixture<OrgComponent>;
    let service: OrgService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [OrgComponent],
      })
        .overrideTemplate(OrgComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OrgComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OrgService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Org(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.orgs && comp.orgs[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});

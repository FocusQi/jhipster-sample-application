import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { OpenerComponent } from 'app/entities/opener/opener.component';
import { OpenerService } from 'app/entities/opener/opener.service';
import { Opener } from 'app/shared/model/opener.model';

describe('Component Tests', () => {
  describe('Opener Management Component', () => {
    let comp: OpenerComponent;
    let fixture: ComponentFixture<OpenerComponent>;
    let service: OpenerService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [OpenerComponent],
      })
        .overrideTemplate(OpenerComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OpenerComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OpenerService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Opener(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.openers && comp.openers[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});

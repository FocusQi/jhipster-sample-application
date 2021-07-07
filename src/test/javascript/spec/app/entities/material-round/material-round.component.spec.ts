import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { MaterialRoundComponent } from 'app/entities/material-round/material-round.component';
import { MaterialRoundService } from 'app/entities/material-round/material-round.service';
import { MaterialRound } from 'app/shared/model/material-round.model';

describe('Component Tests', () => {
  describe('MaterialRound Management Component', () => {
    let comp: MaterialRoundComponent;
    let fixture: ComponentFixture<MaterialRoundComponent>;
    let service: MaterialRoundService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [MaterialRoundComponent],
      })
        .overrideTemplate(MaterialRoundComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MaterialRoundComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MaterialRoundService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new MaterialRound(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.materialRounds && comp.materialRounds[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});

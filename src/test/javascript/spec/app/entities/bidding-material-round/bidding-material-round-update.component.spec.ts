import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { BiddingMaterialRoundUpdateComponent } from 'app/entities/bidding-material-round/bidding-material-round-update.component';
import { BiddingMaterialRoundService } from 'app/entities/bidding-material-round/bidding-material-round.service';
import { BiddingMaterialRound } from 'app/shared/model/bidding-material-round.model';

describe('Component Tests', () => {
  describe('BiddingMaterialRound Management Update Component', () => {
    let comp: BiddingMaterialRoundUpdateComponent;
    let fixture: ComponentFixture<BiddingMaterialRoundUpdateComponent>;
    let service: BiddingMaterialRoundService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [BiddingMaterialRoundUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(BiddingMaterialRoundUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BiddingMaterialRoundUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BiddingMaterialRoundService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new BiddingMaterialRound(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new BiddingMaterialRound();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});

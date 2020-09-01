import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { BiddingVendorRoundUpdateComponent } from 'app/entities/bidding-vendor-round/bidding-vendor-round-update.component';
import { BiddingVendorRoundService } from 'app/entities/bidding-vendor-round/bidding-vendor-round.service';
import { BiddingVendorRound } from 'app/shared/model/bidding-vendor-round.model';

describe('Component Tests', () => {
  describe('BiddingVendorRound Management Update Component', () => {
    let comp: BiddingVendorRoundUpdateComponent;
    let fixture: ComponentFixture<BiddingVendorRoundUpdateComponent>;
    let service: BiddingVendorRoundService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [BiddingVendorRoundUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(BiddingVendorRoundUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BiddingVendorRoundUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BiddingVendorRoundService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new BiddingVendorRound(123);
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
        const entity = new BiddingVendorRound();
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

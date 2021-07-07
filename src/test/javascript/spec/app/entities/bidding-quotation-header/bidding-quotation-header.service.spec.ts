import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT, DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { BiddingQuotationHeaderService } from 'app/entities/bidding-quotation-header/bidding-quotation-header.service';
import { IBiddingQuotationHeader, BiddingQuotationHeader } from 'app/shared/model/bidding-quotation-header.model';
import { ModeType } from 'app/shared/model/enumerations/mode-type.model';
import { BiddingType } from 'app/shared/model/enumerations/bidding-type.model';
import { BiddingStatus } from 'app/shared/model/enumerations/bidding-status.model';

describe('Service Tests', () => {
  describe('BiddingQuotationHeader Service', () => {
    let injector: TestBed;
    let service: BiddingQuotationHeaderService;
    let httpMock: HttpTestingController;
    let elemDefault: IBiddingQuotationHeader;
    let expectedResult: IBiddingQuotationHeader | IBiddingQuotationHeader[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(BiddingQuotationHeaderService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new BiddingQuotationHeader(
        0,
        'AAAAAAA',
        ModeType.BIDDING,
        BiddingType.PO_ORDER_BIDDING,
        currentDate,
        0,
        0,
        0,
        BiddingStatus.BIDDING,
        false,
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        currentDate
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            issuanceDate: currentDate.format(DATE_FORMAT),
            createdDate: currentDate.format(DATE_TIME_FORMAT),
            lastModifiedDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a BiddingQuotationHeader', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            issuanceDate: currentDate.format(DATE_FORMAT),
            createdDate: currentDate.format(DATE_TIME_FORMAT),
            lastModifiedDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            issuanceDate: currentDate,
            createdDate: currentDate,
            lastModifiedDate: currentDate,
          },
          returnedFromService
        );

        service.create(new BiddingQuotationHeader()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a BiddingQuotationHeader', () => {
        const returnedFromService = Object.assign(
          {
            biddingCode: 'BBBBBB',
            modeType: 'BBBBBB',
            biddingType: 'BBBBBB',
            issuanceDate: currentDate.format(DATE_FORMAT),
            biddingTimes: 1,
            sumTimes: 1,
            maxQuotationTimes: 1,
            status: 'BBBBBB',
            costListRequired: true,
            description: 'BBBBBB',
            createdBy: 'BBBBBB',
            createdDate: currentDate.format(DATE_TIME_FORMAT),
            lastModifiedBy: 'BBBBBB',
            lastModifiedDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            issuanceDate: currentDate,
            createdDate: currentDate,
            lastModifiedDate: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of BiddingQuotationHeader', () => {
        const returnedFromService = Object.assign(
          {
            biddingCode: 'BBBBBB',
            modeType: 'BBBBBB',
            biddingType: 'BBBBBB',
            issuanceDate: currentDate.format(DATE_FORMAT),
            biddingTimes: 1,
            sumTimes: 1,
            maxQuotationTimes: 1,
            status: 'BBBBBB',
            costListRequired: true,
            description: 'BBBBBB',
            createdBy: 'BBBBBB',
            createdDate: currentDate.format(DATE_TIME_FORMAT),
            lastModifiedBy: 'BBBBBB',
            lastModifiedDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            issuanceDate: currentDate,
            createdDate: currentDate,
            lastModifiedDate: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a BiddingQuotationHeader', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});

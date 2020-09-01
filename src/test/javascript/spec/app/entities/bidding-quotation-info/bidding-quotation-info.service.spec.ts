import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT, DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { BiddingQuotationInfoService } from 'app/entities/bidding-quotation-info/bidding-quotation-info.service';
import { IBiddingQuotationInfo, BiddingQuotationInfo } from 'app/shared/model/bidding-quotation-info.model';
import { QuotationStatus } from 'app/shared/model/enumerations/quotation-status.model';

describe('Service Tests', () => {
  describe('BiddingQuotationInfo Service', () => {
    let injector: TestBed;
    let service: BiddingQuotationInfoService;
    let httpMock: HttpTestingController;
    let elemDefault: IBiddingQuotationInfo;
    let expectedResult: IBiddingQuotationInfo | IBiddingQuotationInfo[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(BiddingQuotationInfoService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new BiddingQuotationInfo(
        0,
        0,
        0,
        currentDate,
        0,
        0,
        0,
        currentDate,
        0,
        'AAAAAAA',
        QuotationStatus.BEGIN,
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
            validDate: currentDate.format(DATE_FORMAT),
            currentPriceValidDate: currentDate.format(DATE_FORMAT),
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

      it('should create a BiddingQuotationInfo', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            validDate: currentDate.format(DATE_FORMAT),
            currentPriceValidDate: currentDate.format(DATE_FORMAT),
            createdDate: currentDate.format(DATE_TIME_FORMAT),
            lastModifiedDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            validDate: currentDate,
            currentPriceValidDate: currentDate,
            createdDate: currentDate,
            lastModifiedDate: currentDate,
          },
          returnedFromService
        );

        service.create(new BiddingQuotationInfo()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a BiddingQuotationInfo', () => {
        const returnedFromService = Object.assign(
          {
            latestQuotation: 1,
            minQty: 1,
            validDate: currentDate.format(DATE_FORMAT),
            deliveryDays: 1,
            historyPrice: 1,
            currentTaxPrice: 1,
            currentPriceValidDate: currentDate.format(DATE_FORMAT),
            netPrice: 1,
            netUrl: 'BBBBBB',
            status: 'BBBBBB',
            remark: 'BBBBBB',
            createdBy: 'BBBBBB',
            createdDate: currentDate.format(DATE_TIME_FORMAT),
            lastModifiedBy: 'BBBBBB',
            lastModifiedDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            validDate: currentDate,
            currentPriceValidDate: currentDate,
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

      it('should return a list of BiddingQuotationInfo', () => {
        const returnedFromService = Object.assign(
          {
            latestQuotation: 1,
            minQty: 1,
            validDate: currentDate.format(DATE_FORMAT),
            deliveryDays: 1,
            historyPrice: 1,
            currentTaxPrice: 1,
            currentPriceValidDate: currentDate.format(DATE_FORMAT),
            netPrice: 1,
            netUrl: 'BBBBBB',
            status: 'BBBBBB',
            remark: 'BBBBBB',
            createdBy: 'BBBBBB',
            createdDate: currentDate.format(DATE_TIME_FORMAT),
            lastModifiedBy: 'BBBBBB',
            lastModifiedDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            validDate: currentDate,
            currentPriceValidDate: currentDate,
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

      it('should delete a BiddingQuotationInfo', () => {
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

import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { BiddingRoundInfoService } from 'app/entities/bidding-round-info/bidding-round-info.service';
import { IBiddingRoundInfo, BiddingRoundInfo } from 'app/shared/model/bidding-round-info.model';

describe('Service Tests', () => {
  describe('BiddingRoundInfo Service', () => {
    let injector: TestBed;
    let service: BiddingRoundInfoService;
    let httpMock: HttpTestingController;
    let elemDefault: IBiddingRoundInfo;
    let expectedResult: IBiddingRoundInfo | IBiddingRoundInfo[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(BiddingRoundInfoService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new BiddingRoundInfo(0, 0, currentDate, currentDate, 'AAAAAAA', 'AAAAAAA', currentDate, 'AAAAAAA', currentDate);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            startTime: currentDate.format(DATE_TIME_FORMAT),
            endTime: currentDate.format(DATE_TIME_FORMAT),
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

      it('should create a BiddingRoundInfo', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            startTime: currentDate.format(DATE_TIME_FORMAT),
            endTime: currentDate.format(DATE_TIME_FORMAT),
            createdDate: currentDate.format(DATE_TIME_FORMAT),
            lastModifiedDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            startTime: currentDate,
            endTime: currentDate,
            createdDate: currentDate,
            lastModifiedDate: currentDate,
          },
          returnedFromService
        );

        service.create(new BiddingRoundInfo()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a BiddingRoundInfo', () => {
        const returnedFromService = Object.assign(
          {
            round: 1,
            startTime: currentDate.format(DATE_TIME_FORMAT),
            endTime: currentDate.format(DATE_TIME_FORMAT),
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
            startTime: currentDate,
            endTime: currentDate,
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

      it('should return a list of BiddingRoundInfo', () => {
        const returnedFromService = Object.assign(
          {
            round: 1,
            startTime: currentDate.format(DATE_TIME_FORMAT),
            endTime: currentDate.format(DATE_TIME_FORMAT),
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
            startTime: currentDate,
            endTime: currentDate,
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

      it('should delete a BiddingRoundInfo', () => {
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

import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { SrmMsgListService } from 'app/entities/srm-msg-list/srm-msg-list.service';
import { ISrmMsgList, SrmMsgList } from 'app/shared/model/srm-msg-list.model';

describe('Service Tests', () => {
  describe('SrmMsgList Service', () => {
    let injector: TestBed;
    let service: SrmMsgListService;
    let httpMock: HttpTestingController;
    let elemDefault: ISrmMsgList;
    let expectedResult: ISrmMsgList | ISrmMsgList[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(SrmMsgListService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new SrmMsgList(
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        currentDate
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            msgCreateTime: currentDate.format(DATE_TIME_FORMAT),
            msgSendTime: currentDate.format(DATE_TIME_FORMAT),
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

      it('should create a SrmMsgList', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            msgCreateTime: currentDate.format(DATE_TIME_FORMAT),
            msgSendTime: currentDate.format(DATE_TIME_FORMAT),
            createdDate: currentDate.format(DATE_TIME_FORMAT),
            lastModifiedDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            msgCreateTime: currentDate,
            msgSendTime: currentDate,
            createdDate: currentDate,
            lastModifiedDate: currentDate,
          },
          returnedFromService
        );

        service.create(new SrmMsgList()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a SrmMsgList', () => {
        const returnedFromService = Object.assign(
          {
            msgId: 1,
            msgTopic: 'BBBBBB',
            msgContent: 'BBBBBB',
            msgMailTo: 'BBBBBB',
            msgSmsTo: 'BBBBBB',
            msgCreateTime: currentDate.format(DATE_TIME_FORMAT),
            msgSendTime: currentDate.format(DATE_TIME_FORMAT),
            msgStatus: 'BBBBBB',
            msfMemo: 'BBBBBB',
            msgToBackUp: 'BBBBBB',
            remark: 'BBBBBB',
            lastModifiedBy: 'BBBBBB',
            createdBy: 'BBBBBB',
            createdDate: currentDate.format(DATE_TIME_FORMAT),
            lastModifiedDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            msgCreateTime: currentDate,
            msgSendTime: currentDate,
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

      it('should return a list of SrmMsgList', () => {
        const returnedFromService = Object.assign(
          {
            msgId: 1,
            msgTopic: 'BBBBBB',
            msgContent: 'BBBBBB',
            msgMailTo: 'BBBBBB',
            msgSmsTo: 'BBBBBB',
            msgCreateTime: currentDate.format(DATE_TIME_FORMAT),
            msgSendTime: currentDate.format(DATE_TIME_FORMAT),
            msgStatus: 'BBBBBB',
            msfMemo: 'BBBBBB',
            msgToBackUp: 'BBBBBB',
            remark: 'BBBBBB',
            lastModifiedBy: 'BBBBBB',
            createdBy: 'BBBBBB',
            createdDate: currentDate.format(DATE_TIME_FORMAT),
            lastModifiedDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            msgCreateTime: currentDate,
            msgSendTime: currentDate,
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

      it('should delete a SrmMsgList', () => {
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

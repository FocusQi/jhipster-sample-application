import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { BomTemplateInfoColumnService } from 'app/entities/bom-template-info-column/bom-template-info-column.service';
import { IBomTemplateInfoColumn, BomTemplateInfoColumn } from 'app/shared/model/bom-template-info-column.model';

describe('Service Tests', () => {
  describe('BomTemplateInfoColumn Service', () => {
    let injector: TestBed;
    let service: BomTemplateInfoColumnService;
    let httpMock: HttpTestingController;
    let elemDefault: IBomTemplateInfoColumn;
    let expectedResult: IBomTemplateInfoColumn | IBomTemplateInfoColumn[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(BomTemplateInfoColumnService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new BomTemplateInfoColumn(0, 0, 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a BomTemplateInfoColumn', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new BomTemplateInfoColumn()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a BomTemplateInfoColumn', () => {
        const returnedFromService = Object.assign(
          {
            sortIndex: 1,
            columnName: 'BBBBBB',
            columnValue: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of BomTemplateInfoColumn', () => {
        const returnedFromService = Object.assign(
          {
            sortIndex: 1,
            columnName: 'BBBBBB',
            columnValue: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a BomTemplateInfoColumn', () => {
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

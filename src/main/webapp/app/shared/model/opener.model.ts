import { Moment } from 'moment';
import { IBiddingQuotationHeader } from 'app/shared/model/bidding-quotation-header.model';

export interface IOpener {
  id?: number;
  userId?: string;
  secretKey?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastModifiedBy?: string;
  lastModifiedDate?: Moment;
  bqHeaders?: IBiddingQuotationHeader[];
}

export class Opener implements IOpener {
  constructor(
    public id?: number,
    public userId?: string,
    public secretKey?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastModifiedBy?: string,
    public lastModifiedDate?: Moment,
    public bqHeaders?: IBiddingQuotationHeader[]
  ) {}
}

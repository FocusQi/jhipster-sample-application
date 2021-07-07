import { Moment } from 'moment';
import { IBiddingQuotationHeader } from 'app/shared/model/bidding-quotation-header.model';

export interface IBiddingOpener {
  id?: number;
  userId?: string;
  secretKey?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastModifiedBy?: string;
  lastModifiedDate?: Moment;
  bqHeader?: IBiddingQuotationHeader;
}

export class BiddingOpener implements IBiddingOpener {
  constructor(
    public id?: number,
    public userId?: string,
    public secretKey?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastModifiedBy?: string,
    public lastModifiedDate?: Moment,
    public bqHeader?: IBiddingQuotationHeader
  ) {}
}

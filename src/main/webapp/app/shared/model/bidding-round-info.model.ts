import { Moment } from 'moment';
import { IVendorRound } from 'app/shared/model/vendor-round.model';
import { IMaterialRound } from 'app/shared/model/material-round.model';
import { IBiddingQuotationHeader } from 'app/shared/model/bidding-quotation-header.model';

export interface IBiddingRoundInfo {
  id?: number;
  round?: number;
  startTime?: Moment;
  endTime?: Moment;
  description?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastModifiedBy?: string;
  lastModifiedDate?: Moment;
  vendorRounds?: IVendorRound[];
  materialRounds?: IMaterialRound[];
  header?: IBiddingQuotationHeader;
}

export class BiddingRoundInfo implements IBiddingRoundInfo {
  constructor(
    public id?: number,
    public round?: number,
    public startTime?: Moment,
    public endTime?: Moment,
    public description?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastModifiedBy?: string,
    public lastModifiedDate?: Moment,
    public vendorRounds?: IVendorRound[],
    public materialRounds?: IMaterialRound[],
    public header?: IBiddingQuotationHeader
  ) {}
}

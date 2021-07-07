import { Moment } from 'moment';
import { IBiddingVendorRound } from 'app/shared/model/bidding-vendor-round.model';
import { IBiddingMaterialRound } from 'app/shared/model/bidding-material-round.model';
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
  vendorRounds?: IBiddingVendorRound[];
  materialRounds?: IBiddingMaterialRound[];
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
    public vendorRounds?: IBiddingVendorRound[],
    public materialRounds?: IBiddingMaterialRound[],
    public header?: IBiddingQuotationHeader
  ) {}
}

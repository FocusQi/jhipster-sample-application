import { Moment } from 'moment';
import { IVendor } from 'app/shared/model/vendor.model';
import { IBiddingRoundInfo } from 'app/shared/model/bidding-round-info.model';

export interface IBiddingVendorRound {
  id?: number;
  createdBy?: string;
  createdDate?: Moment;
  lastModifiedBy?: string;
  lastModifiedDate?: Moment;
  vendor?: IVendor;
  roundInfo?: IBiddingRoundInfo;
}

export class BiddingVendorRound implements IBiddingVendorRound {
  constructor(
    public id?: number,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastModifiedBy?: string,
    public lastModifiedDate?: Moment,
    public vendor?: IVendor,
    public roundInfo?: IBiddingRoundInfo
  ) {}
}

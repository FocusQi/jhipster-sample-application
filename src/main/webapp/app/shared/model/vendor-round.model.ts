import { Moment } from 'moment';
import { IBiddingRoundInfo } from 'app/shared/model/bidding-round-info.model';
import { IVendor } from 'app/shared/model/vendor.model';

export interface IVendorRound {
  id?: number;
  createdBy?: string;
  createdDate?: Moment;
  lastModifiedBy?: string;
  lastModifiedDate?: Moment;
  roundInfo?: IBiddingRoundInfo;
  vendor?: IVendor;
}

export class VendorRound implements IVendorRound {
  constructor(
    public id?: number,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastModifiedBy?: string,
    public lastModifiedDate?: Moment,
    public roundInfo?: IBiddingRoundInfo,
    public vendor?: IVendor
  ) {}
}

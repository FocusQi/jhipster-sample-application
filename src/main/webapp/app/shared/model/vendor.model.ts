import { IVendorRound } from 'app/shared/model/vendor-round.model';

export interface IVendor {
  id?: number;
  vendorRounds?: IVendorRound[];
}

export class Vendor implements IVendor {
  constructor(public id?: number, public vendorRounds?: IVendorRound[]) {}
}

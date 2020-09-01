export interface IVendor {
  id?: number;
}

export class Vendor implements IVendor {
  constructor(public id?: number) {}
}

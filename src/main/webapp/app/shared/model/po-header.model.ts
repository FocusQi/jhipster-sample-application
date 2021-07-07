import { IPriceCompare } from 'app/shared/model/price-compare.model';

export interface IPoHeader {
  id?: number;
  priceCompare?: IPriceCompare;
}

export class PoHeader implements IPoHeader {
  constructor(public id?: number, public priceCompare?: IPriceCompare) {}
}

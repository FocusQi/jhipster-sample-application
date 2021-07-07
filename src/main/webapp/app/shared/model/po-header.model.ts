import { IBiddingPriceCompare } from 'app/shared/model/bidding-price-compare.model';

export interface IPoHeader {
  id?: number;
  priceCompare?: IBiddingPriceCompare;
}

export class PoHeader implements IPoHeader {
  constructor(public id?: number, public priceCompare?: IBiddingPriceCompare) {}
}

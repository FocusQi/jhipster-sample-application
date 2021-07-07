import { Moment } from 'moment';
import { IBiddingMaterialRound } from 'app/shared/model/bidding-material-round.model';
import { IBiddingPriceCompare } from 'app/shared/model/bidding-price-compare.model';
import { QuotationStatus } from 'app/shared/model/enumerations/quotation-status.model';

export interface IBiddingQuotationInfo {
  id?: number;
  latestQuotation?: number;
  minQty?: number;
  validDate?: Moment;
  deliveryDays?: number;
  historyPrice?: number;
  currentTaxPrice?: number;
  currentPriceValidDate?: Moment;
  netPrice?: number;
  netUrl?: string;
  status?: QuotationStatus;
  remark?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastModifiedBy?: string;
  lastModifiedDate?: Moment;
  materialRound?: IBiddingMaterialRound;
  priceCompare?: IBiddingPriceCompare;
}

export class BiddingQuotationInfo implements IBiddingQuotationInfo {
  constructor(
    public id?: number,
    public latestQuotation?: number,
    public minQty?: number,
    public validDate?: Moment,
    public deliveryDays?: number,
    public historyPrice?: number,
    public currentTaxPrice?: number,
    public currentPriceValidDate?: Moment,
    public netPrice?: number,
    public netUrl?: string,
    public status?: QuotationStatus,
    public remark?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastModifiedBy?: string,
    public lastModifiedDate?: Moment,
    public materialRound?: IBiddingMaterialRound,
    public priceCompare?: IBiddingPriceCompare
  ) {}
}

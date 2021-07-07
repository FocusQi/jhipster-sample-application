import { Moment } from 'moment';
import { IMaterialRound } from 'app/shared/model/material-round.model';
import { IPriceCompare } from 'app/shared/model/price-compare.model';
import { QuotationStatus } from 'app/shared/model/enumerations/quotation-status.model';

export interface IQuotationInfo {
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
  materialRound?: IMaterialRound;
  priceCompare?: IPriceCompare;
}

export class QuotationInfo implements IQuotationInfo {
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
    public materialRound?: IMaterialRound,
    public priceCompare?: IPriceCompare
  ) {}
}

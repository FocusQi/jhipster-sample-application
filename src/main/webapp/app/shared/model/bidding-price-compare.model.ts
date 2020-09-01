import { Moment } from 'moment';
import { IBiddingQuotationInfo } from 'app/shared/model/bidding-quotation-info.model';
import { IPoHeader } from 'app/shared/model/po-header.model';
import { IBiddingQuotationHeader } from 'app/shared/model/bidding-quotation-header.model';
import { CompareStatus } from 'app/shared/model/enumerations/compare-status.model';

export interface IBiddingPriceCompare {
  id?: number;
  compareCode?: string;
  status?: CompareStatus;
  description?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastModifiedBy?: string;
  lastModifiedDate?: Moment;
  quotationInfos?: IBiddingQuotationInfo[];
  poHeaders?: IPoHeader[];
  bqHeader?: IBiddingQuotationHeader;
}

export class BiddingPriceCompare implements IBiddingPriceCompare {
  constructor(
    public id?: number,
    public compareCode?: string,
    public status?: CompareStatus,
    public description?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastModifiedBy?: string,
    public lastModifiedDate?: Moment,
    public quotationInfos?: IBiddingQuotationInfo[],
    public poHeaders?: IPoHeader[],
    public bqHeader?: IBiddingQuotationHeader
  ) {}
}

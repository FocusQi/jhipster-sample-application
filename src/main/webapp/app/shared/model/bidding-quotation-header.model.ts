import { Moment } from 'moment';
import { IPriceCompare } from 'app/shared/model/price-compare.model';
import { IOpener } from 'app/shared/model/opener.model';
import { IBiddingRoundInfo } from 'app/shared/model/bidding-round-info.model';
import { ModeType } from 'app/shared/model/enumerations/mode-type.model';
import { BiddingType } from 'app/shared/model/enumerations/bidding-type.model';
import { BiddingStatus } from 'app/shared/model/enumerations/bidding-status.model';

export interface IBiddingQuotationHeader {
  id?: number;
  biddingCode?: string;
  modeType?: ModeType;
  biddingType?: BiddingType;
  issuanceDate?: Moment;
  biddingTimes?: number;
  sumTimes?: number;
  maxQuotationTimes?: number;
  status?: BiddingStatus;
  costListRequired?: boolean;
  description?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastModifiedBy?: string;
  lastModifiedDate?: Moment;
  priceCompare?: IPriceCompare;
  openers?: IOpener[];
  roundInfos?: IBiddingRoundInfo[];
}

export class BiddingQuotationHeader implements IBiddingQuotationHeader {
  constructor(
    public id?: number,
    public biddingCode?: string,
    public modeType?: ModeType,
    public biddingType?: BiddingType,
    public issuanceDate?: Moment,
    public biddingTimes?: number,
    public sumTimes?: number,
    public maxQuotationTimes?: number,
    public status?: BiddingStatus,
    public costListRequired?: boolean,
    public description?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastModifiedBy?: string,
    public lastModifiedDate?: Moment,
    public priceCompare?: IPriceCompare,
    public openers?: IOpener[],
    public roundInfos?: IBiddingRoundInfo[]
  ) {
    this.costListRequired = this.costListRequired || false;
  }
}

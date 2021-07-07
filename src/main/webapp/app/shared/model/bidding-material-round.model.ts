import { Moment } from 'moment';
import { IBiddingQuotationInfo } from 'app/shared/model/bidding-quotation-info.model';
import { IBomTemplateHeader } from 'app/shared/model/bom-template-header.model';
import { IMaterial } from 'app/shared/model/material.model';
import { IBiddingRoundInfo } from 'app/shared/model/bidding-round-info.model';

export interface IBiddingMaterialRound {
  id?: number;
  needQty?: number;
  priceCeiling?: number;
  fileUrl?: number;
  createdBy?: string;
  createdDate?: Moment;
  lastModifiedBy?: string;
  lastModifiedDate?: Moment;
  quotationInfos?: IBiddingQuotationInfo[];
  bomTemplateHeaders?: IBomTemplateHeader[];
  material?: IMaterial;
  roundInfo?: IBiddingRoundInfo;
}

export class BiddingMaterialRound implements IBiddingMaterialRound {
  constructor(
    public id?: number,
    public needQty?: number,
    public priceCeiling?: number,
    public fileUrl?: number,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastModifiedBy?: string,
    public lastModifiedDate?: Moment,
    public quotationInfos?: IBiddingQuotationInfo[],
    public bomTemplateHeaders?: IBomTemplateHeader[],
    public material?: IMaterial,
    public roundInfo?: IBiddingRoundInfo
  ) {}
}

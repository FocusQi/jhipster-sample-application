import { Moment } from 'moment';
import { IQuotationInfo } from 'app/shared/model/quotation-info.model';
import { IBomTemplateHeader } from 'app/shared/model/bom-template-header.model';
import { IBiddingRoundInfo } from 'app/shared/model/bidding-round-info.model';
import { IMaterial } from 'app/shared/model/material.model';

export interface IMaterialRound {
  id?: number;
  needQty?: number;
  priceCeiling?: number;
  fileUrl?: number;
  createdBy?: string;
  createdDate?: Moment;
  lastModifiedBy?: string;
  lastModifiedDate?: Moment;
  quotationInfos?: IQuotationInfo[];
  bomTemplateHeaders?: IBomTemplateHeader[];
  roundInfo?: IBiddingRoundInfo;
  material?: IMaterial;
}

export class MaterialRound implements IMaterialRound {
  constructor(
    public id?: number,
    public needQty?: number,
    public priceCeiling?: number,
    public fileUrl?: number,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastModifiedBy?: string,
    public lastModifiedDate?: Moment,
    public quotationInfos?: IQuotationInfo[],
    public bomTemplateHeaders?: IBomTemplateHeader[],
    public roundInfo?: IBiddingRoundInfo,
    public material?: IMaterial
  ) {}
}

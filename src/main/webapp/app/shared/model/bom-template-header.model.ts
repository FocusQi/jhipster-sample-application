import { Moment } from 'moment';
import { IBomTemplateInfo } from 'app/shared/model/bom-template-info.model';
import { IMaterialRound } from 'app/shared/model/material-round.model';

export interface IBomTemplateHeader {
  id?: number;
  templateCode?: string;
  templateName?: string;
  templateType?: string;
  remark?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastModifiedBy?: string;
  lastModifiedDate?: Moment;
  infos?: IBomTemplateInfo[];
  materialRound?: IMaterialRound;
}

export class BomTemplateHeader implements IBomTemplateHeader {
  constructor(
    public id?: number,
    public templateCode?: string,
    public templateName?: string,
    public templateType?: string,
    public remark?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastModifiedBy?: string,
    public lastModifiedDate?: Moment,
    public infos?: IBomTemplateInfo[],
    public materialRound?: IMaterialRound
  ) {}
}

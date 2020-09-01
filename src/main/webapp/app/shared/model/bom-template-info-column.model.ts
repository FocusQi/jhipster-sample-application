import { IBomTemplateInfo } from 'app/shared/model/bom-template-info.model';

export interface IBomTemplateInfoColumn {
  id?: number;
  sortIndex?: number;
  columnName?: string;
  columnValue?: string;
  info?: IBomTemplateInfo;
}

export class BomTemplateInfoColumn implements IBomTemplateInfoColumn {
  constructor(
    public id?: number,
    public sortIndex?: number,
    public columnName?: string,
    public columnValue?: string,
    public info?: IBomTemplateInfo
  ) {}
}

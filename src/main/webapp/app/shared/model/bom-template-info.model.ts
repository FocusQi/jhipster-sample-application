import { Moment } from 'moment';
import { IBomTemplateInfoColumn } from 'app/shared/model/bom-template-info-column.model';
import { IBomTemplateHeader } from 'app/shared/model/bom-template-header.model';

export interface IBomTemplateInfo {
  id?: number;
  uom?: string;
  useQty?: number;
  unitPrice?: number;
  total?: number;
  tax?: number;
  taxTotal?: number;
  remark?: number;
  createdBy?: string;
  createdDate?: Moment;
  lastModifiedBy?: string;
  lastModifiedDate?: Moment;
  columns?: IBomTemplateInfoColumn[];
  header?: IBomTemplateHeader;
}

export class BomTemplateInfo implements IBomTemplateInfo {
  constructor(
    public id?: number,
    public uom?: string,
    public useQty?: number,
    public unitPrice?: number,
    public total?: number,
    public tax?: number,
    public taxTotal?: number,
    public remark?: number,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastModifiedBy?: string,
    public lastModifiedDate?: Moment,
    public columns?: IBomTemplateInfoColumn[],
    public header?: IBomTemplateHeader
  ) {}
}

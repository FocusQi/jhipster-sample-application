import { Moment } from 'moment';

export interface IReturnHeader {
  id?: number;
  fromNum?: number;
  vCode?: string;
  mCode?: string;
  quantity?: number;
  createdBy?: string;
  createdDate?: Moment;
  lastModifiedBy?: string;
  lastModifiedDate?: Moment;
}

export class ReturnHeader implements IReturnHeader {
  constructor(
    public id?: number,
    public fromNum?: number,
    public vCode?: string,
    public mCode?: string,
    public quantity?: number,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastModifiedBy?: string,
    public lastModifiedDate?: Moment
  ) {}
}

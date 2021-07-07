import { Moment } from 'moment';

export interface IOpener {
  id?: number;
  userId?: string;
  secretKey?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastModifiedBy?: string;
  lastModifiedDate?: Moment;
}

export class Opener implements IOpener {
  constructor(
    public id?: number,
    public userId?: string,
    public secretKey?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastModifiedBy?: string,
    public lastModifiedDate?: Moment
  ) {}
}

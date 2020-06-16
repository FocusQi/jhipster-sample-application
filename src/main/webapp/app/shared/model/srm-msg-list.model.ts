import { Moment } from 'moment';

export interface ISrmMsgList {
  id?: number;
  msgId?: number;
  msgTopic?: string;
  msgContent?: string;
  msgMailTo?: string;
  msgSmsTo?: string;
  msgCreateTime?: Moment;
  msgSendTime?: Moment;
  msgStatus?: string;
  msfMemo?: string;
  msgToBackUp?: string;
  remark?: string;
  lastModifiedBy?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastModifiedDate?: Moment;
}

export class SrmMsgList implements ISrmMsgList {
  constructor(
    public id?: number,
    public msgId?: number,
    public msgTopic?: string,
    public msgContent?: string,
    public msgMailTo?: string,
    public msgSmsTo?: string,
    public msgCreateTime?: Moment,
    public msgSendTime?: Moment,
    public msgStatus?: string,
    public msfMemo?: string,
    public msgToBackUp?: string,
    public remark?: string,
    public lastModifiedBy?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastModifiedDate?: Moment
  ) {}
}

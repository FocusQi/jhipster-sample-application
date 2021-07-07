export interface IOrg {
  id?: number;
  name?: string;
}

export class Org implements IOrg {
  constructor(public id?: number, public name?: string) {}
}

export interface IMaterial {
  id?: number;
}

export class Material implements IMaterial {
  constructor(public id?: number) {}
}

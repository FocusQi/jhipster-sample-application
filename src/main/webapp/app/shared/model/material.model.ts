import { IMaterialRound } from 'app/shared/model/material-round.model';

export interface IMaterial {
  id?: number;
  materialRounds?: IMaterialRound[];
}

export class Material implements IMaterial {
  constructor(public id?: number, public materialRounds?: IMaterialRound[]) {}
}

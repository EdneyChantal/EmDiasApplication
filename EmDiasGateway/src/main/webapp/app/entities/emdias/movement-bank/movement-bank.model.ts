import * as dayjs from 'dayjs';
import { IAccountBank } from 'app/entities/emdias/account-bank/account-bank.model';
import { INaturePlan } from 'app/entities/emdias/nature-plan/nature-plan.model';

export interface IMovementBank {
  id?: number;
  movementDate?: dayjs.Dayjs | null;
  movementValue?: number | null;
  history?: string | null;
  numberDoc?: string | null;
  accid?: string | null;
  accountBank?: IAccountBank | null;
  naturePlan?: INaturePlan | null;
}

export class MovementBank implements IMovementBank {
  constructor(
    public id?: number,
    public movementDate?: dayjs.Dayjs | null,
    public movementValue?: number | null,
    public history?: string | null,
    public numberDoc?: string | null,
    public accid?: string | null,
    public accountBank?: IAccountBank | null,
    public naturePlan?: INaturePlan | null
  ) {}
}

export function getMovementBankIdentifier(movementBank: IMovementBank): number | undefined {
  return movementBank.id;
}

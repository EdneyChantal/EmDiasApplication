import { IWorkSpace } from 'app/entities/emdias/work-space/work-space.model';

export interface IAccountBank {
  id?: number;
  nomeDaContaBancaria?: string | null;
  valorInicial?: number | null;
  codContaExtrato?: string | null;
  digito?: string | null;
  workSpace?: IWorkSpace | null;
}

export class AccountBank implements IAccountBank {
  constructor(
    public id?: number,
    public nomeDaContaBancaria?: string | null,
    public valorInicial?: number | null,
    public codContaExtrato?: string | null,
    public digito?: string | null,
    public workSpace?: IWorkSpace | null
  ) {}
}

export function getAccountBankIdentifier(accountBank: IAccountBank): number | undefined {
  return accountBank.id;
}

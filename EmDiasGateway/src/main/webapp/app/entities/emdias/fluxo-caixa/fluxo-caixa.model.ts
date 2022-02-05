import * as dayjs from 'dayjs';

export interface ILinhaFluxoCaixa{
  id?: number;
  descricao?: string | null;
  coluna1x?: string | null;
  coluna2x?: string | null;
  coluna3x?: string | null;
  coluna4x?: string | null;
  coluna5x?: string | null;
  coluna6x?: string | null;
  coluna7x?: string | null;
  coluna8x?: string | null;
  coluna9x?: string | null;
  coluna10x?: string | null;
  coluna11x?: string | null;
  coluna12x?: string | null;
  coluna13x?: string | null;
  coluna14x?: string | null;
  coluna15x?: string | null;
  coluna16x?: string | null;
  coluna17x?: string | null;
  coluna18x?: string | null;
  coluna19x?: string | null;
  coluna20x?: string | null;
  coluna21x?: string | null;
  coluna22x?: string | null;
  coluna23x?: string | null;
  coluna24x?: string | null;
  coluna25x?: string | null;
  coluna26x?: string | null;
  coluna27x?: string | null;
  coluna28x?: string | null;
  coluna29x?: string | null;
  coluna30x?: string | null;
  coluna31x?: string | null;
  coluna32x?: string | null;
}
export interface IConfiguracaoColunas {
  title?:string | null;
  field?:string | null;
}

export interface IFluxoCaixa {
  linhaFluxoCaixaMesDTOSet?:Array<ILinhaFluxoCaixa>;
  configuracaoColunas?:Array<IConfiguracaoColunas>;
}

export interface IParametroFluxoCaixa {
  dataFinal?: dayjs.Dayjs,
  dataInicial?: dayjs.Dayjs,
  workSpaceId?:  number
}

export class LinhaFluxoCaixa implements ILinhaFluxoCaixa {
    constructor(public id?: number,
                  public descricao?: string | null,
                  public coluna1?: string | null,
                  public coluna2?: string | null,
                  public coluna3?: string | null,
                  public coluna4?: string | null,
                  public coluna5?: string | null,
                  public coluna6?: string | null,
                  public coluna7?: string | null,
                  public coluna8?: string | null,
                  public coluna9?: string | null,
                  public coluna10?: string | null,
                  public coluna11?: string | null,
                  public coluna12?: string | null,
                  public coluna13?: string | null,
                  public coluna14?: string | null,
                  public coluna15?: string | null,
                  public coluna16?: string | null,
                  public coluna17?: string | null,
                  public coluna18?: string | null,
                  public coluna19?: string | null,
                  public coluna20?: string | null,
                  public coluna21?: string | null,
                  public coluna22?: string | null,
                  public coluna23?: string | null,
                  public coluna24?: string | null,
                  public coluna25?: string | null,
                  public coluna26?: string | null,
                  public coluna27?: string | null,
                  public coluna28?: string | null,
                  public coluna29?: string | null,
                  public coluna30?: string | null,
                  public coluna31?: string | null) {}
}
export class ConfiguracaoColunas implements IConfiguracaoColunas {
  constructor( public title?:string | null,
               public field?:string | null){}

}

export class FluxoCaixa implements IFluxoCaixa {
  constructor( public linhaFluxoCaixaMesDTOSet?:Array<ILinhaFluxoCaixa>,
               public configuracaoColunas?:Array<IConfiguracaoColunas>) {
               }
}

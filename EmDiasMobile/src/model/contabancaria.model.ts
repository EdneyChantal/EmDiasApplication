import { WorkSpace } from "./workspace.model";


export class ContaBancaria {
    constructor(
      public id: number, 
      public workSpace: WorkSpace,
      public nomeDaContaBancaria: string , 
      public valorInicial: number , 
      public codContaExtrato: string , 
      public digito: string ,
      public tipo: string
    ){}
}
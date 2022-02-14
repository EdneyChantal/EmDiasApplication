

export class ContaBancaria {
    constructor(
      public id: number, 
      public workSpace: string,
      public nomeDaContaBancaria: string , 
      public valorInicial: number , 
      public codContaExtrato: string , 
      public digito: string ,
      public tipo: string
    ){}
}
import { ContaBancaria } from "./contabancaria.model";
import { NaturePlan } from "./naturePlan.model";

export class MovementBank {
   constructor(
      public id?:number,
      public accountBank?:ContaBancaria,
      public naturePlan?:NaturePlan,
      public movementDate?:Date,
      public movementValue?:number,
      public history?:string,
      public number_doc?:string,
      public accid?:string 
   ) {}
}
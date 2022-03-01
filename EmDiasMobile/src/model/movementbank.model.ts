import { ContaBancaria } from "./contabancaria.model";
import { NaturePlan } from "./naturePlan.model";

export class MovementBank {
   constructor(
      id?:number,
      accountBank?:ContaBancaria,
      naturePlan?:NaturePlan,
      movementDate?:Date,
      movementValue?:number,
      history?:string,
      number_doc?:string,
      accid?:string 
   ) {}
}
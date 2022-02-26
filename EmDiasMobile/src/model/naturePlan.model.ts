import { WorkSpace } from "./workspace.model";

export class NaturePlan {
    constructor(
        id:number,
        workSpace:WorkSpace,
        descNaturePlan:string , 
        typeNaturePlan:string ,
        ind_control_orcamento:string,
        naturePlanFather:NaturePlan
    ){}
}
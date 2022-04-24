import { WorkSpace } from "./workspace.model";

export class NaturePlan {
    constructor(
        public id:number,
        public workSpace?:WorkSpace,
        public descNaturePlan?:string , 
        public typeNaturePlan?:string ,
        public ind_control_orcamento?:string,
        public naturePlanFather?:NaturePlan
    ){}
}
import { IWorkSpace } from 'app/entities/emdias/work-space/work-space.model';
import { TypeNaturePlan } from 'app/entities/enumerations/type-nature-plan.model';

export interface INaturePlan {
  id?: number;
  descNaturePlan?: string | null;
  typeNaturePlan?: TypeNaturePlan | null;
  workSpace?: IWorkSpace | null;
}

export class NaturePlan implements INaturePlan {
  constructor(
    public id?: number,
    public descNaturePlan?: string | null,
    public typeNaturePlan?: TypeNaturePlan | null,
    public workSpace?: IWorkSpace | null
  ) {}
}

export function getNaturePlanIdentifier(naturePlan: INaturePlan): number | undefined {
  return naturePlan.id;
}

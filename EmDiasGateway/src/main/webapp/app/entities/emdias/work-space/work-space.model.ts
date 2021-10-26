export interface IWorkSpace {
  id?: number;
  nome?: string | null;
}

export class WorkSpace implements IWorkSpace {
  constructor(public id?: number, public nome?: string | null) {}
}

export function getWorkSpaceIdentifier(workSpace: IWorkSpace): number | undefined {
  return workSpace.id;
}

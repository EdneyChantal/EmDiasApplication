import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IWorkSpace } from '../work-space.model';

@Component({
  selector: 'jhi-work-space-detail',
  templateUrl: './work-space-detail.component.html',
})
export class WorkSpaceDetailComponent implements OnInit {
  workSpace: IWorkSpace | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ workSpace }) => {
      this.workSpace = workSpace;
    });
  }

  previousState(): void {
    window.history.back();
  }
}

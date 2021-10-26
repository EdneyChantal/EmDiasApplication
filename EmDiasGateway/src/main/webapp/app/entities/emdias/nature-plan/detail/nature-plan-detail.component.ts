import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INaturePlan } from '../nature-plan.model';

@Component({
  selector: 'jhi-nature-plan-detail',
  templateUrl: './nature-plan-detail.component.html',
})
export class NaturePlanDetailComponent implements OnInit {
  naturePlan: INaturePlan | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ naturePlan }) => {
      this.naturePlan = naturePlan;
    });
  }

  previousState(): void {
    window.history.back();
  }
}

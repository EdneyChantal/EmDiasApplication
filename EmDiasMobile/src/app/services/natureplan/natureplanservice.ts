import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ContaBancaria } from 'src/model/contabancaria.model';
import { environment } from '../../../environments/environment';
import { Observable, Subject } from 'rxjs';
import { NaturePlan } from 'src/model/naturePlan.model';

@Injectable({
  providedIn: 'root'
})
export class NaturePlanService {
  public static API_URL = environment.apiUrl.replace('api','services') + '/emdias/api/nature-plans';

  constructor(public http: HttpClient) { }


  getAllFromWorkSpace(id:number): Observable<NaturePlan[]>{
    return this.http.get<NaturePlan[]>(NaturePlanService.API_URL + '/' + 'workspace/' + id );
  }
}


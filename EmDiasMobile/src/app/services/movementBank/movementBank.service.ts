import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ContaBancaria } from 'src/model/contabancaria.model';
import { environment } from '../../../environments/environment';
import { Observable, Subject } from 'rxjs';
import { NaturePlan } from 'src/model/naturePlan.model';
import { MovementBank } from 'src/model/movementbank.model';

@Injectable({
  providedIn: 'root'
})
export class MovementBankService {
  public static API_URL = environment.apiUrl.replace('api','services') + '/emdias/api/movement-banks';

  constructor(public http: HttpClient) { }


  getAllFromWorkSpace(id:number): Observable<NaturePlan[]>{
    return this.http.get<NaturePlan[]>(MovementBankService.API_URL + '/' + 'workspace/' + id );
  }

  gravarMovementBank(body: MovementBank):Observable<MovementBank> {
    return this.http.post<MovementBank>(MovementBankService.API_URL , body);
  }

}


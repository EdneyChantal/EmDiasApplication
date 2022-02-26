import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ContaBancaria } from 'src/model/contabancaria.model';
import { environment } from '../../../environments/environment';
import { Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmdiasService {
  public static API_URL = environment.apiUrl.replace('api','services') + '/emdias/api';

  constructor(public http: HttpClient) { }
  getAccountBanks(): Observable<ContaBancaria[]>{
    return this.http.get<ContaBancaria[]>(EmdiasService.API_URL + '/' + 'account-banks');
  }
  getAccountBank(id:string): Observable<ContaBancaria>{
    return this.http.get<ContaBancaria>(EmdiasService.API_URL + '/' + 'account-banks/' + id );
  }
  getSaldoConta(id:string): Observable<number>{
    return this.http.get<number>(EmdiasService.API_URL + '/' + 'movement-banks/' + id + '/saldo');
  }
 
 
  post(endpoint: string, body: any, reqOpts?: any) {
    return this.http.post(EmdiasService.API_URL + '/' + endpoint, body, reqOpts);
  }
  put(endpoint: string, body: any, reqOpts?: any) {
    return this.http.put(EmdiasService.API_URL + '/' + endpoint, body, reqOpts);
  }
  delete(endpoint: string, reqOpts?: any) {
    return this.http.delete(EmdiasService.API_URL + '/' + endpoint, reqOpts);
  }
  patch(endpoint: string, body: any, reqOpts?: any) {
    return this.http.put(EmdiasService.API_URL + '/' + endpoint, body, reqOpts);
  }
}

import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { IFluxoCaixa,IParametroFluxoCaixa } from '../fluxo-caixa.model';
import { createRequestOption } from 'app/core/request/request-util';

export type EntityResponseType = HttpResponse<IFluxoCaixa>;

@Injectable({ providedIn: 'root' })
export class FluxoCaixaService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/fluxocaixa/gerar', 'emdias');

   constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}



   query(parameto:IParametroFluxoCaixa): Observable<EntityResponseType> {
      const copy = this.convertDateFromClient(parameto);
       return this.http.post<IFluxoCaixa>(this.resourceUrl, copy, { observe: 'response' });
   }

   protected convertDateFromClient(parametro: IParametroFluxoCaixa): IParametroFluxoCaixa {
       return Object.assign({}, parametro, {
         dataInicial: parametro.dataInicial?.isValid() ? parametro.dataInicial.toJSON() : undefined,
         dataFinal: parametro.dataFinal?.isValid() ? parametro.dataFinal.toJSON() : undefined,
       });
   }
}

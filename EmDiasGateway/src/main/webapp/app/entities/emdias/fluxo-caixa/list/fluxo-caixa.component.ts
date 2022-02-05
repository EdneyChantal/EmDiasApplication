import { Component, OnInit } from '@angular/core';
import * as dayjs from 'dayjs';
import {IFluxoCaixa }  from '../fluxo-caixa.model';
import { FluxoCaixaService } from  '../service/fluxo-caixa.service';
import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'jhi-fluxo-caixa',
  templateUrl: './fluxo-caixa.component.html',
  styleUrls: ['./fluxo-caixa.component.css']
})

export class FluxoCaixaComponent implements OnInit {
  fluxoCaixa?:IFluxoCaixa;
  isLoading = false;

  constructor(protected fluxoCaixaService: FluxoCaixaService) {}
   loadAll(): void {
    this.isLoading = true;
    this.fluxoCaixaService.query({ dataFinal: dayjs('2021-01-30T09:53:35.423Z'),
                                   dataInicial: dayjs('2021-01-01T09:53:35.423Z'),
                                   workSpaceId: 1
                                 }).subscribe(
      (res: HttpResponse<IFluxoCaixa>) => {
        this.isLoading = false;
        this.fluxoCaixa = res.body ?? {};
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  buscaCabecalhoItem (textofield?:string):string {
    if (!this.fluxoCaixa || !this.fluxoCaixa.configuracaoColunas) {
       return '';
    }
    const elemento=this.fluxoCaixa.configuracaoColunas.find(x=>x.field===textofield);
    if (elemento) {
       return elemento.title?elemento.title:'';
    }
    return '';
  }
  ngOnInit(): void {
      this.loadAll();
  }

}

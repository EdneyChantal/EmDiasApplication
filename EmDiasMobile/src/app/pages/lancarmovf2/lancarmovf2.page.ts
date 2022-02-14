import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { EmdiasService } from 'src/app/services/emdias/emdias.service';
import { ContaBancaria } from 'src/model/contabancaria.model';

@Component({
  selector: 'app-lancarmovf2',
  templateUrl: './lancarmovf2.page.html',
  styleUrls: ['./lancarmovf2.page.scss'],
})
export class Lancarmovf2Page implements OnInit {
  idconta:string;
  contaBancaria:ContaBancaria;
  constructor(private route: ActivatedRoute,private emdiasService:EmdiasService) { }

  ngOnInit() {
      this.idconta =  this.route.snapshot.paramMap.get('idconta');
      this.emdiasService.getAccountBank(this.idconta).subscribe(conta=>{
        this.contaBancaria = conta; 
      })
   
  }

}

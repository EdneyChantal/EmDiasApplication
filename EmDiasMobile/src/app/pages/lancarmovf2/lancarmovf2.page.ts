import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { EmdiasService } from 'src/app/services/emdias/emdias.service';
import { NaturePlanService } from 'src/app/services/natureplan/natureplanservice';
import { ContaBancaria } from 'src/model/contabancaria.model';
import { MovementBank } from 'src/model/movementbank.model';
import { NaturePlan } from 'src/model/naturePlan.model';
import { format, utcToZonedTime } from 'date-fns-tz';
import { formatISO } from 'date-fns';  

@Component({
  selector: 'app-lancarmovf2',
  templateUrl: './lancarmovf2.page.html',
  styleUrls: ['./lancarmovf2.page.scss'],
})
export class Lancarmovf2Page implements OnInit {
  userTimeZone = Intl.DateTimeFormat().resolvedOptions().timeZone;
  idconta:string;
  contaBancaria:ContaBancaria;
  movementBank:MovementBank={};
  datachoose:any =  formatISO(new Date());
  saldoConta:number; 
  naturePlans:NaturePlan[];
  openModelData:boolean=false;
  constructor(private route: ActivatedRoute,
              private emdiasService:EmdiasService,
              private natService:NaturePlanService) { }

  toogleOpenModelData(){
    this.openModelData = ! this.openModelData;
  }            

  ngOnInit() {
      this.idconta =  this.route.snapshot.paramMap.get('idconta');
      this.emdiasService.getAccountBank(this.idconta).subscribe(conta=>{
        this.contaBancaria = conta; 
      });
      this.emdiasService.getSaldoConta(this.idconta).subscribe
      ((saldo)=>this.saldoConta=saldo);
      this.natService.getAllFromWorkSpace(1).subscribe((nats)=>{
        this.naturePlans= nats;
      });
   
  }

}

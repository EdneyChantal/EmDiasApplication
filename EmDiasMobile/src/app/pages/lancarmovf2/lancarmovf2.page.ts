import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { EmdiasService } from 'src/app/services/emdias/emdias.service';
import { NaturePlanService } from 'src/app/services/natureplan/natureplanservice';
import { ContaBancaria } from 'src/model/contabancaria.model';
import { MovementBank } from 'src/model/movementbank.model';
import { NaturePlan } from 'src/model/naturePlan.model';
import { format, utcToZonedTime } from 'date-fns-tz';
import { formatISO } from 'date-fns';  
import { MovementBankService } from 'src/app/services/movementBank/movementBank.service';
import { ToastController } from '@ionic/angular';

@Component({
  selector: 'app-lancarmovf2',
  templateUrl: './lancarmovf2.page.html',
  styleUrls: ['./lancarmovf2.page.scss'],
})
export class Lancarmovf2Page implements OnInit {
  userTimeZone = Intl.DateTimeFormat().resolvedOptions().timeZone;
  idconta:string;
  contaBancaria:ContaBancaria;
  movementBank:MovementBank=new MovementBank(null,null,new NaturePlan(null),null,null,null,null,null);
  datachoose:any =  formatISO(new Date());
  saldoConta:number; 
  naturePlans:NaturePlan[];
  openModelData:boolean=false;
  constructor(private route: ActivatedRoute,
              private emdiasService:EmdiasService,
              private natService:NaturePlanService,
              private mbService:MovementBankService,
              public toastController: ToastController) { }

  toogleOpenModelData(){
    this.openModelData = ! this.openModelData;
  }            

  ngOnInit() {
      this.idconta =  this.route.snapshot.paramMap.get('idconta');
      this.newMov();
      this.emdiasService.getAccountBank(this.idconta).subscribe(conta=>{
        this.contaBancaria = conta; 
      });
      this.pegarSaldoConta();
      this.natService.getAllFromWorkSpace(1).subscribe((nats)=>{
        this.naturePlans= nats;
      });
   
  }
  newMov() {
    this.movementBank=new MovementBank(null,new ContaBancaria(+this.idconta),new NaturePlan(null),null,null,null,null,null);
  }
  pegarSaldoConta() {
    this.emdiasService.getSaldoConta(this.idconta).subscribe
    ((saldo)=>this.saldoConta=saldo);
  }
  doSave() {
     this.movementBank.movementDate = this.datachoose; 
     this.mbService.gravarMovementBank(this.movementBank).subscribe(
        async () => {
          const toast = await this.toastController.create({
            message: "Gravou com Sucesso",
            duration: 3000,
            position: 'top',
          });
          toast.present();
          this.newMov();
          this.pegarSaldoConta();
        }
        ,async (response)=>{
          const error = JSON.parse(response.error);
          const toast = await this.toastController.create({
            message: error,
            duration: 3000,
            position: 'middle',
          });
          toast.present();

      }
    )  
  }
}

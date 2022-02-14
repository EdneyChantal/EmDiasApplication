import { Component, OnInit } from '@angular/core';
import { EmdiasService } from 'src/app/services/emdias/emdias.service';
import { ContaBancaria } from 'src/model/contabancaria.model';


@Component({
  selector: 'app-lancarmovf1',
  templateUrl: './lancarmovf1.page.html',
  styleUrls: ['./lancarmovf1.page.scss'],
})
export class Lancarmovf1Page implements OnInit {
  constructor(public api:EmdiasService) { }
  contasBancaria:ContaBancaria[];
  ngOnInit() {
      this.api.getAccountBanks().subscribe( 
        (response)=>{ 
          this.contasBancaria = response;
        },(err=>{
          console.log(err);
        })
        );
  }

}

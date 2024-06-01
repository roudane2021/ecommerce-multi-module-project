import { ChangeDetectionStrategy, Component, OnInit, ViewChild } from '@angular/core';
import { productEffect } from '../../../ng-RX/effects/product.effect';
import { ChildComponent } from '../child/child.component';

export interface Telephone{
  name:string;
  prix:number
}

@Component({
  selector: 'app-parent',
  templateUrl: './parent.component.html',
  styleUrls: ['./parent.component.scss'],
  //changeDetection: ChangeDetectionStrategy.OnPush


})
export class ParentComponent implements OnInit{
  @ViewChild(ChildComponent, {static: true})
  child! : ChildComponent;
  ngOnInit(): void {
    console.log(' ngOnInit Parent!!!!')
    this.child.test();
  }

  tele : Telephone = {name: 'Redmi Note 12 Pro Plus', prix : 12};

  updateTele(){

    //this.tele = {...this.tele, prix : this.tele.prix + 1 };

      this.tele.prix += 1;


  }

}

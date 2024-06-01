import { AfterContentChecked, Component, EventEmitter, Input, Output } from '@angular/core';
import { ActionP, Produit, ResponseEvent } from 'src/app/produit-achat/models/produit.model';

@Component({
  selector: 'app-item-list-produit',
  templateUrl: './item-list-produit.component.html',
  styleUrls: ['./item-list-produit.component.scss']
})
export class ItemListProduitComponent {
  private _produit!: Produit;
  @Output() event: EventEmitter<ResponseEvent> = new EventEmitter<ResponseEvent>();
  actionDetails: ActionP = ActionP.DETAILS;
  actionPanier: ActionP = ActionP.PANIER;

  @Input() 
  set produit(value: Produit) {
     // Check if the incoming value is null or an empty object
     if (this.isNullOrEmpty(value)) {
      console.warn('Warning: produit is null or an empty object.');
    } else {
      // Assign the valid value
      this._produit = value;
    }
  }

  get produit(): Produit{
    return this._produit;
  }

  private isNullOrEmpty(obj: any): boolean {
    return obj === null || (Object.keys(obj).length === 0 && obj.constructor === Object);
  }

  onAction(action : ActionP): void {
    this.event.emit({event: action, idP: this.produit.id!});
    }

}

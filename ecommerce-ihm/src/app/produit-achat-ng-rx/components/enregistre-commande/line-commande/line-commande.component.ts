import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, AbstractControl } from '@angular/forms';

@Component({
  selector: 'app-line-commande',
  templateUrl: './line-commande.component.html',
  styleUrls: ['./line-commande.component.scss']
})
export class LineCommandeComponent implements OnInit{

  @Input() lineProduit!: FormGroup ;
  @Input() lineIndex!: number;
  @Output() eventDelete: EventEmitter<number> = new EventEmitter<number>();

  ngOnInit(): void {

  }

  deleteLine(): void {
    this.eventDelete.emit(this.lineIndex);
  }

}

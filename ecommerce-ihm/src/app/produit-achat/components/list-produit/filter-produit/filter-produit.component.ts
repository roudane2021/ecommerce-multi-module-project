import { Component, EventEmitter, OnInit, Output, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable, combineLatest, map, tap } from 'rxjs';
import { Criteria, Operator, OperatorOptions } from 'src/app/produit-achat/models/produit.model';

@Component({
  selector: 'app-filter-produit',
  templateUrl: './filter-produit.component.html',
  styleUrls: ['./filter-produit.component.scss']
})
export class FilterProduitComponent implements OnInit {

  private formBuilder: FormBuilder = inject(FormBuilder);
  filterForm!: FormGroup;
  prixGroup!: FormGroup;
  titreGroup!: FormGroup;
  operators: any[] = OperatorOptions;
  @Output() event: EventEmitter<Criteria[]> = new EventEmitter<Criteria[]>();

  ngOnInit(): void {
    this.initFormControls();
    this.initFilterForm();
  }

  private initFilterForm = () : void => {
    this.filterForm = this.formBuilder.group({
      prix: this.prixGroup,
      titre: this.titreGroup

    });
  }
  private initFormControls = () : void => {
    this.prixGroup = this.formBuilder.group({
    name: this.formBuilder.control('prix', [Validators.required]),
    value: this.formBuilder.control('', [Validators.required]),
    operator: this.formBuilder.control(Operator.EQUALS, [Validators.required]),

    });
    this.titreGroup = this.formBuilder.group({
      name: this.formBuilder.control('titre', [Validators.required]),
      value: this.formBuilder.control('', [Validators.required]),
      operator: this.formBuilder.control(Operator.EQUALS, [Validators.required]),
  
      });

      
  }


 

  onFilterForm() : void {
   console.log('11111111111');
    if (this.titreGroup.invalid || this.prixGroup.invalid) {
      return;
    }
    this.event.emit([this.prixGroup.value, this.titreGroup.value]);
  }

}

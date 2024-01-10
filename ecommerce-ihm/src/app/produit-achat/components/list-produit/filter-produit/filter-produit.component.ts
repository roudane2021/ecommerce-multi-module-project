import { AfterContentChecked, ChangeDetectionStrategy, ChangeDetectorRef, Component, EventEmitter, Input, OnDestroy, OnInit, Output, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable, combineLatest, filter, map, takeUntil, tap, startWith, Subject, skip } from 'rxjs';
import { Criteria, Operator, OperatorOptions } from 'src/app/produit-achat/models/produit.model';

@Component({
  selector: 'app-filter-produit',
  templateUrl: './filter-produit.component.html',
  styleUrls: ['./filter-produit.component.scss'],
})
export class FilterProduitComponent implements OnInit , OnDestroy{


  private formBuilder: FormBuilder = inject(FormBuilder);
  private ref: ChangeDetectorRef = inject(ChangeDetectorRef);
  filterForm!: FormGroup;
  prixGroup!: FormGroup;
  titreGroup!: FormGroup;
  operators: any[] = OperatorOptions;
  @Output()  sendFilter: EventEmitter<Criteria[]> = new EventEmitter<Criteria[]>();
  @Output()  sendApplyFilter: EventEmitter<boolean> = new EventEmitter<boolean>();
  private unsubscribe$ : Subject<void> = new Subject<void>();



  ngOnInit(): void {
   
    this.initFormControls();
    this.initFilterForm();
    this.initObservable();
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
    operator: this.formBuilder.control('0', [Validators.required]),

    });
    this.titreGroup = this.formBuilder.group({
      name: this.formBuilder.control('titre', [Validators.required]),
      value: this.formBuilder.control('', [Validators.required]),
      operator: this.formBuilder.control('0', [Validators.required]),
  
      });      
  }

  private initObservable(): void {
    const prix$: Observable<Criteria> = this.prixGroup.valueChanges.pipe(startWith({} as Criteria),takeUntil(this.unsubscribe$));
    const titre$: Observable<Criteria> = this.titreGroup.valueChanges.pipe(startWith({} as Criteria),takeUntil(this.unsubscribe$));
  
    combineLatest([titre$, prix$]).pipe(
      takeUntil(this.unsubscribe$),
      skip(1),
      tap(([titre, prix]) => {
          this.transferFilter([titre, prix]);
      })
    ).subscribe();
  }


  private transferFilter = (filters : Criteria[]): void =>  {
    this.sendFilter.emit(filters);
  }
 

  onSendApplyFilter() : void {
    this.sendApplyFilter.emit(true);
   
  }

  InitialiserApplyFilter() : void {
    this.filterForm.get('prix')?.get('value')?.reset();
    this.filterForm.get('titre')?.get('value')?.reset();
   this.sendApplyFilter.emit(true);
   
  }

  ngOnDestroy(): void {
    this.unsubscribe$.complete();
    
  }

}

import { Component, OnDestroy, OnInit, ChangeDetectionStrategy, inject } from '@angular/core';
import { AbstractControl, FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Observable, Subject, map, startWith, takeUntil, tap } from 'rxjs';
import { confirmEqualValidator } from 'src/app/shared/validators/confirm-equal.validator';
import { CommandeService } from '../../services/commende.service';

import { UtilsService } from '../../../shared/services/utils.service';
import { UserService } from '../../../shared/services/user.service';
import { emailValidator } from '../../../shared/validators/email.validator';
import { usernameValidator } from '../../../shared/validators/username.validator';
import { ActivatedRoute, Router } from '@angular/router';
import { Store } from '@ngrx/store';
import * as appState from '../../ng-rx/state/state';
import * as commandeAction from '../../ng-rx/actions/commande.action';
import * as commandeSelector from '../../ng-rx/selectors/commande.selector';
import { Commande, CommandeForm } from '../../models/commande.model';

@Component({
  selector: 'app-commande',
  templateUrl: './creation-commande.component.html',
  styleUrls: ['./creation-commande.component.scss'],
  changeDetection: ChangeDetectionStrategy.Default
})
export class CreationCommandeComponent implements OnInit, OnDestroy{




  private readonly _commandeService: CommandeService = inject(CommandeService);
  private readonly _userService: UserService = inject(UserService);
  private readonly _utilsService: UtilsService = inject(UtilsService);
  private readonly _router: Router = inject(Router);
  private readonly _route: ActivatedRoute = inject(ActivatedRoute);
  private readonly store: Store<any> = inject(Store<appState.FeatureState>);


  commandeForm!: FormGroup;
  userInfoForm!: FormGroup;
  emailForm!: FormGroup;
  loginInfoForm!: FormGroup;

  productFormArray!: FormArray<FormGroup>;


  contactPreferenceCtrl!: FormControl;
  emailCtrl! : FormControl;
  confirmEmailCtrl!: FormControl;
  usernameCtrl!: FormControl;
  passwordCtrl!: FormControl;
  confirmPasswordCtrl!: FormControl;
  phoneCtrl!: FormControl;
  paysCtrl!: FormControl;
  villeCtrl!: FormControl;

  showEmail$!: Observable<boolean>;
  showPhone$!: Observable<boolean>;
  showEmailError$!: Observable<boolean>;
  showPasswordError$!: Observable<boolean>;
  destroy$: Subject<void> = new Subject<void>(); 

  pays$!: Observable<string[]>;
  ville$!: Observable<string[]>;
   $$event: any;



  ngOnInit() : void{
    this.initForm();
    this.initObservables();
  }

  initForm = (): void => {
    
    this.contactPreferenceCtrl = new FormControl('email');

    this.emailCtrl = new FormControl('', [Validators.required, Validators.email], [emailValidator(this._userService)]);
    this.confirmEmailCtrl = new FormControl('', [Validators.required, Validators.email]);

    this.phoneCtrl = new FormControl('', [Validators.required]);

    this.paysCtrl = new FormControl('', [Validators.required]);
    this.villeCtrl = new FormControl('', [Validators.required]);

    this.userInfoForm = new FormGroup({
      'firstName': new FormControl('', [Validators.required]),
      'lastName': new FormControl('', [Validators.required]),
      'pays': this.paysCtrl,
      'ville' : this.villeCtrl
    });

    this.emailForm = new FormGroup({
      'email': this.emailCtrl,
      'confirmEmail': this.confirmEmailCtrl
    },{
      validators: [confirmEqualValidator('email','confirmEmail')],
      updateOn: 'change'
    });

    this.usernameCtrl = new FormControl('', [Validators.required], [usernameValidator(this._userService)]);
    this.passwordCtrl = new FormControl('');
    this.confirmPasswordCtrl = new FormControl('');

    this.loginInfoForm = new FormGroup({
      'username': this.usernameCtrl,
      'password': this.passwordCtrl,
      'confirmPassword': this.confirmPasswordCtrl
    },{
      validators: [confirmEqualValidator('password', 'confirmPassword')],
      updateOn: 'change'
    });

    this.productFormArray = new FormArray([] as FormGroup[]);

    this.commandeForm = new FormGroup({
      'userInfo': this.userInfoForm,
      'contactPreference': this.contactPreferenceCtrl,
      'email': this.emailForm,
      'phone': this.phoneCtrl,
      'loginInfo': this.loginInfoForm,
      'ligneCommandes': this.productFormArray
    });
  }

  initObservables = (): void => {

    this.pays$ = this._utilsService.pays$.pipe(takeUntil(this.destroy$));
    this.ville$ = this._utilsService.ville$;
    this._utilsService.construirePays();

    this.showEmail$ = this.contactPreferenceCtrl.valueChanges.pipe(
      startWith(this.contactPreferenceCtrl.value),
      takeUntil(this.destroy$),
       map( data => data === 'email'),
       tap(useEmail => this.setEmailValidators(useEmail))
    );

    this.showPhone$ = this.contactPreferenceCtrl.valueChanges.pipe(
      startWith(this.contactPreferenceCtrl.value),
      takeUntil(this.destroy$),
       map( data => data === 'phone'),
       tap(usePhone => this.setPhoneValidators(usePhone))
    );

    this.showEmailError$ = this.emailForm.statusChanges.pipe(
      takeUntil(this.destroy$),
      map(status => (status === 'INVALID' && this.emailCtrl.value != '' &&
                      this.confirmEmailCtrl.value != '' && this.emailForm.hasError('confirmEqual'))),
    );

    this.showPasswordError$ = this.loginInfoForm.statusChanges.pipe(
      takeUntil(this.destroy$),
      map(status => (status === 'INVALID' && this.loginInfoForm.hasError('confirmEqual'))),
    );

   this.paysCtrl.valueChanges.pipe(
       takeUntil(this.destroy$),
       tap(pays => this.resetVillesList(pays))
   ).subscribe();

   this.store.select(commandeSelector.createCommandeSelector)
   .pipe(
    takeUntil(this.destroy$),
    tap((commande : appState.CreateCommandeState) => {
      this.createCommandeStatus(commande);
    })
   ).subscribe();
    

   
  }

  private setPhoneValidators(usePhone: boolean): void {
    if (usePhone){
      this.phoneCtrl.addValidators([Validators.required]);
      this.emailForm.reset();
    }
    else {
      this.phoneCtrl.removeValidators(Validators.required);
    }
    this.phoneCtrl.updateValueAndValidity();
  }
  private setEmailValidators(useEmail: boolean): void {
    if(useEmail) {
      this.emailCtrl.addValidators([Validators.required, Validators.email]);
      this.emailCtrl.addAsyncValidators([emailValidator(this._userService)]);
      this.confirmEmailCtrl.addValidators([Validators.required, Validators.email]);
      this.phoneCtrl.reset();
    }else{
      this.emailCtrl.clearValidators();
      this.emailCtrl.clearAsyncValidators();
      this.confirmEmailCtrl.clearValidators();
    }
    this.emailCtrl.updateValueAndValidity();
    this.confirmEmailCtrl.updateValueAndValidity();

  }

  getFormControlErrorText(ctrl: AbstractControl): string {
      

  if (ctrl.hasError('email')){
      return 'Merci d\'entrer une adresse mail valide';
  } 
  else if (ctrl.hasError('minlength')) {
      return 'Ce numéro de téléphone ne contient pas assez de chiffres';
  } else if (ctrl.hasError('maxlength')) {
      return 'Ce numéro de téléphone contient trop de chiffres';
  }
  else if (ctrl.hasError('usernameValidator')) {
    return 'UserName already <strong>exists</strong>';
  }
  else if (ctrl.hasError('emailValidator')) {
    return 'Email already <strong>exists</strong>';
  }
  else {
    return `Ce  champ est requis`;
  }

    }

    resetVillesList = (pays: string): void  => {
      this.villeCtrl.reset();
     this._utilsService.construireVille(pays);
    }

    onSubmit() {
      if(this.commandeForm.invalid){
        return;
      }
      const commande: Commande = this.mapCommandeFormToCommande(this.commandeForm.value);
      this.store.dispatch(commandeAction.CommandesActions.commandeActionAddStart({ commande }));
      //this._router.navigate(['../list-commande'],{relativeTo: this._route});

    }

     mapCommandeFormToCommande(form: CommandeForm): Commande {
      // Map ligneCommandes from produits
      const ligneCommandes = form.ligneCommandes?.map(produit => ({
        quantite: produit.quantite,
        produitId: produit.produitId
      })) || [];
    
      // Create the Commande object
      const commande: Commande = {
        firstName: form.userInfo.firstName,
        lastName: form.userInfo.lastName,
        email: form.email?.email,
        phone: form.contactPreference === 'phone' ? form.phone : undefined,
        username: form.loginInfo.username,
        password: form.loginInfo.password,
        ligneCommandes: ligneCommandes
      };
    
      return commande;
    }
  
  addProduitToList = (): void => {
    const produitItem = new FormGroup({
      produitId: new FormControl('', [Validators.required]),
      quantite: new FormControl('', [Validators.required])
    });
    this.productFormArray.push(produitItem);
    }


  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
    
  }

  removeProductItem(index: number) {
    this.productFormArray.removeAt(index);
    }

    createCommandeStatus(commande: appState.CreateCommandeState) {
      
      if (commande?.isCreated) {
        this._router.navigate(['../list-commande'],{relativeTo: this._route});
      }
      
    }

}



import { Component, Inject, OnInit, inject } from '@angular/core';
import { AbstractControl, FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { CommandeService } from '../../services/commande.service';
import { Observable, map, startWith, tap } from 'rxjs';
import { confirmEqualValidator } from 'src/app/shared/validators/confirm-equal.validator';
import { usernameValidator } from '../../../shared/validators/username.validator';

@Component({
  selector: 'app-enregistre-commande',
  templateUrl: './enregistre-commande.component.html',
  styleUrls: ['./enregistre-commande.component.scss']
})
export class EnregistreCommandeComponent implements OnInit{



  private formBuilder: FormBuilder = inject(FormBuilder);
  private commandeService: CommandeService = inject(CommandeService);

  mainForm!: FormGroup;
  contactPreferenceCtrl!: FormControl;
  phoneCtrl!: FormControl;

  userInfoForm!: FormGroup;
  firstNameCtrl!: FormControl;
  lastNameCtrl!: FormControl;

  emailForm!: FormGroup;
  emailCtrl!: FormControl;
  confirmEmailCtrl!: FormControl;

  loginUserForm!: FormGroup;
  userNameCtrl!: FormControl;
  passwordCtrl!: FormControl;
  confirmPasswordCtrl!: FormControl;



  showEmailCtrl$!: Observable<Boolean>;
  showPhoneCtrl$!: Observable<Boolean>;

  showEmailError$!: Observable<Boolean>;
  showPasswordError$!: Observable<Boolean>;

  showFormMainError$!: Observable<Boolean>;

  ngOnInit(): void {
   this.initFormCtrls();
   this.initFormObservables();
  }

  initFormCtrls = () : void => {
    this.contactPreferenceCtrl = this.formBuilder.control('email');
    this.phoneCtrl = this.formBuilder.control('', [Validators.required]);

    this.firstNameCtrl = this.formBuilder.control('', [Validators.required]);
    this.lastNameCtrl = this.formBuilder.control('', [Validators.required]);
    this.userInfoForm = this.formBuilder.group({
      firstName: this.firstNameCtrl,
      lastName: this.lastNameCtrl
    });

    this.emailCtrl = this.formBuilder.control('', [Validators.required, Validators.email]);
    this.confirmEmailCtrl = this.formBuilder.control('', [Validators.required, Validators.email]);
    this.emailForm = this.formBuilder.group({
      email: this.emailCtrl,
      cofirmEmail: this.confirmEmailCtrl
    },{
      validators: [confirmEqualValidator('email','cofirmEmail')],
      updateOn: 'change'
    });

    

    this.userNameCtrl = this.formBuilder.control('', {
      validators: [Validators.required],
      asyncValidators: [usernameValidator(this.commandeService)],
      updateOn: 'blur'
    });
    this.passwordCtrl = this.formBuilder.control('', [Validators.required]);
    this.confirmPasswordCtrl = this.formBuilder.control('', {
      validators: [Validators.required],
      updateOn: 'blur'
    });
    this.loginUserForm =  this.formBuilder.group({
      username: this.userNameCtrl,
      password: this.passwordCtrl,
      confirmPassword: this.confirmPasswordCtrl
    },{
      validators: [confirmEqualValidator('password','confirmPassword')],
      updateOn: 'blur'
    });   
    
   
    this.mainForm = this.formBuilder.group({
      userInfo: this.userInfoForm,
      contactPreference: this.contactPreferenceCtrl,
      email: this.emailForm,
      phone: this.phoneCtrl,
      loginInfo: this.loginUserForm,
      produits: new FormArray([])
      });

  };


  addProduitToList(): void {
    const line : FormGroup = this.formBuilder.group({
      produitID: this.formBuilder.control('', [Validators.required]),
      qte: this.formBuilder.control('',  [Validators.required]),
    });
    this.listProduit.push(line);
    
  }

  get listProduit() : FormArray{
    return this.mainForm.get('produits') as FormArray;
  }

  transferToFormGroup(abstractControl: AbstractControl): FormGroup {
     return abstractControl as FormGroup;
    }
  deleteElement(lineIndex: number) {
      this.listProduit.removeAt(lineIndex);
      }

  initFormObservables = () : void => {
    this.showEmailCtrl$ = this.contactPreferenceCtrl.valueChanges.pipe(
      startWith(this.contactPreferenceCtrl.value),
      map(preference => preference === 'email'),
      tap(showEmail => this.setEmailValidators(showEmail))
    );

    this.showPhoneCtrl$ = this.contactPreferenceCtrl.valueChanges.pipe(
      startWith(this.contactPreferenceCtrl.value),
      map(preference => preference === 'phone'),
      tap(showPhone => this.setPhoneValidators(showPhone))
    );

    this.showEmailError$ = this.emailForm.statusChanges.pipe(
      map(status => status === 'INVALID' &&
      this.emailCtrl.value &&
      this.confirmEmailCtrl.value)
    );

    this.showPasswordError$ = this.loginUserForm.statusChanges.pipe(
      map(status => status === 'INVALID' &&
      this.passwordCtrl.value &&
      this.confirmPasswordCtrl.value && this.loginUserForm.hasError('confirmEqual'))
      
    );

    this.showFormMainError$ = this.mainForm.statusChanges.pipe(
      startWith('INVALID'),
      map(status => status === 'INVALID')
    );

  };

  getFormControlErrorText(ctrl : AbstractControl) {
    if (ctrl.hasError('required')) {
      return 'Ce champ est requis';
  } else if (ctrl.hasError('email')) {
      return 'Merci d\'entrer une adresse mail valide';
  } else if (ctrl.hasError('minlength')) {
      return 'Ce numéro de téléphone ne contient pas assez de chiffres';
  } else if (ctrl.hasError('maxlength')) {
      return 'Ce numéro de téléphone contient trop de chiffres';
  }else if (ctrl.hasError('validValidator')) {
    return 'Ce texte ne contient pas le mot Rachid';
}  else if (ctrl.hasError('usernameValidator')) {
  return 'Username dèja Exist !!!';
}

else {
      return 'Ce champ contient une erreur';
  }
  }

  private setEmailValidators(showEmail: boolean): void {
    if(showEmail) {
      this.emailCtrl.addValidators([Validators.required, Validators.email]);
      this.confirmEmailCtrl.addValidators([Validators.required, Validators.email]);
      this.phoneCtrl.reset();
    }
    else {
      this.emailCtrl.clearValidators();
      this.confirmEmailCtrl.clearValidators();
    }
    this.emailCtrl.updateValueAndValidity();
    this.confirmEmailCtrl.updateValueAndValidity();

  }
  private setPhoneValidators(showPhone: boolean): void {
    if(showPhone) {
      this.phoneCtrl.addValidators([Validators.required, Validators.minLength(10), Validators.maxLength(10)]);
      this.emailForm.reset();
    }
    else {
      this.phoneCtrl.clearValidators();
    }
    this.phoneCtrl.updateValueAndValidity();
  }

  onSubmitForm() {
    if (this.mainForm.invalid){
      console.log(`Erreur ...`);
      return;
    }
    console.log(this.mainForm.value);
    this.mainForm.reset();
    this.contactPreferenceCtrl.setValue('email');
}




}

import { CommandeService } from "../../produit-achat/services/commande.service";
import { AbstractControl, AsyncValidatorFn, ValidationErrors } from '@angular/forms';
import { Observable, map } from "rxjs";


export function usernameValidator(commandeService: CommandeService): AsyncValidatorFn {


    return (controle : AbstractControl) : Observable<ValidationErrors | null> => {
          console.table(commandeService)
        return commandeService.checkUsername(controle.value).pipe(
            map(isUnique => isUnique ? null : {usernameValidator: true})
        )
    }

}
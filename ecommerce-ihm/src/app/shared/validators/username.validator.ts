import { CommandeService } from "../../produit-achat/services/commande.service";
import { AbstractControl, AsyncValidatorFn, ValidationErrors } from '@angular/forms';
import { Observable, map, tap } from "rxjs";
import { UserService } from "../services/user.service";


export function usernameValidator(userService: UserService): AsyncValidatorFn {


    return (controle : AbstractControl) : Observable<ValidationErrors | null> => {
        return userService.checkUsername(controle.value).pipe(
            map(isUnique => !isUnique ? null : {usernameValidator: true})
        )
    }

}
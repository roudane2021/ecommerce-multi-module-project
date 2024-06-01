import { AsyncValidatorFn, AbstractControl, ValidationErrors } from '@angular/forms';
import { Observable, map, of } from 'rxjs';
import { UserService } from '../services/user.service';


export function emailValidator(userService : UserService): AsyncValidatorFn {

    return (ctrl: AbstractControl): Observable<ValidationErrors | null>=> {

        const email: string = ctrl.value;
        return userService.existingEmail(email).pipe(
            map(isExist => isExist ? {
                emailValidator: 'Email already exist !!!!!'
            } : null)
        );
    }
}
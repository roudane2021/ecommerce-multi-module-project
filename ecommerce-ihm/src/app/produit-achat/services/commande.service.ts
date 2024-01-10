import { Injectable } from "@angular/core";
import { Observable, delay, of } from "rxjs";

@Injectable()
export class CommandeService{


    checkUsername(username : string): Observable<Boolean> {

        if (username === 'rachid') {
            return of(true).pipe(delay(1000));  
        }

        return of(false).pipe(delay(1000));
    }

}
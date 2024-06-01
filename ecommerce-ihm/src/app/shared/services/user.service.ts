import { Injectable, inject } from "@angular/core";
import { Observable, delay, map, of } from "rxjs";
import { HttpClient } from '@angular/common/http';


@Injectable({
    providedIn: 'root'
})
export class UserService {

    private _httpClient: HttpClient = inject(HttpClient);
    private _existingUserName = of(['rachid', 'imane', 'sanae', 'abdo']);
    private _existingEmail = of(['rachid@gmail.com', 'rachidroudane1994@gmail.com']);
    

    checkUsername = (username: string): Observable<boolean> => {
        return this._existingUserName.pipe(
            delay(1000),
            map((existingUserName: string[]) => existingUserName.some(u => u === username))
        )
    };

    existingEmail = (email: string): Observable<boolean> => {
        return this._existingEmail.pipe(
            delay(1000),
            map((existingEmail: string[]) => existingEmail.some(e => e === email))
    
        )
    };

}
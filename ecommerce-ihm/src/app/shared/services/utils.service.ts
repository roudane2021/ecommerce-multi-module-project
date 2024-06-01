import { Injectable } from "@angular/core";
import { BehaviorSubject, Observable } from "rxjs";

@Injectable({providedIn: "root"})
export class UtilsService {
    private pays$_ : BehaviorSubject<string[]> = new BehaviorSubject<string[]>([]);
    private ville$_ : BehaviorSubject<string[]> = new BehaviorSubject<string[]>([]);

    get pays$(): Observable<string[]> {

        return this.pays$_.asObservable();
    }
    get ville$(): Observable<string[]> {

        return this.ville$_.asObservable();
    }



    construirePays(): void {

        const pays = ["Maroc", "Algérie", "Libye", "Mauritanie"];

        this.pays$_.next(pays);
    }

    construireVille(pays : string): void {

        let villes : string[] = [];

        switch(pays) {
            case 'Maroc':
                villes = ["Casablanca", "Rabat", "Marrakech", "Fès", "Tanger", "Agadir", "Oujda", "Kenitra", "Tétouan", "Salé", "Nador", "Beni Mellal", "Témara", "Khouribga", "Mohammedia", "El Jadida", "Ouarzazate", "Safi", "Laâyoune", "Errachidia"];
            break;
            case 'Algérie':
                villes = ["Alger", "Oran", "Constantine", "Annaba", "Blida", "Batna", "Djelfa", "Sétif", "Sidi Bel Abbès", "Biskra", "Tébessa", "El Oued", "Skikda", "Tiaret", "Béjaïa", "Tlemcen", "Béchar", "Mostaganem", "El Eulma", "Chlef"];
            break;
            case 'Libye':
                villes = ["Tripoli", "Benghazi", "Misrata", "Tarhuna", "Al-Zawiya", "Zliten", "Al-Marj", "Zuwara", "Ajdabiya", "Sabha", "Sirte", "Tobrouk", "Derna", "Zintan", "Gharyan", "Nalut", "Yafran", "Bani Walid", "Waddan", "Ubari"];
            break;
            case 'Mauritanie':
                villes = ["Nouakchott", "Nouadhibou", "Néma", "Kaédi", "Rosso", "Zouérat", "Atar", "Aleg", "Kiffa", "Tidjikja", "Aioun", "Akjoujt", "Boutilimit", "Sélibaby", "Arafat", "Oualata", "Bir Moghrein", "Bababé", "Bogué", "Magta-Lahjar"];
            break;
        }

        this.ville$_.next(villes);

    }
}
import { BehaviorSubject } from "rxjs";
import { Produit } from "../models/produit.model";


export class ProductService {

    private readonly  _product$: BehaviorSubject<Produit[]> = new BehaviorSubject<Produit[]>([]);

    get product() {
        
        return this._product$.asObservable();
    }

    doawloadProduct(): void {

        this._product$.next(produits);


    }

}

export const produits: Produit[] = [
    { id: 1, titre: 'iPhone 13', description: 'Le dernier smartphone d\'Apple', prix: 1099 },
    { id: 2, titre: 'Samsung Galaxy S21', description: 'Smartphone haut de gamme de Samsung', prix: 1049 },
    { id: 3, titre: 'Sony PlayStation 5', description: 'Console de jeu de nouvelle génération', prix: 499 },
    { id: 4, titre: 'Samsung QLED 4K TV', description: 'Téléviseur 4K haut de gamme de Samsung', prix: 899 },
    { id: 5, titre: 'MacBook Pro 16"', description: 'Ordinateur portable haut de gamme d\'Apple', prix: 2399 },
    { id: 6, titre: 'Nike Air Max 270', description: 'Baskets de course pour hommes', prix: 129 },
    { id: 7, titre: 'Canon EOS R5', description: 'Appareil photo sans miroir haut de gamme de Canon', prix: 3899 },
    { id: 8, titre: 'Dell XPS 15', description: 'Ordinateur portable ultraportable de Dell', prix: 1499 },
    { id: 9, titre: 'LEGO Star Wars Millennium Falcon', description: 'Maquette LEGO de l\'emblématique vaisseau spatial', prix: 799 },
    { id: 10, titre: 'Fitbit Charge 5', description: 'Bracelet connecté avec suivi de la fréquence cardiaque', prix: 179 },
    { id: 11, titre: 'Samsung Odyssey G9', description: 'Moniteur de jeu incurvé ultralarge', prix: 1399 },
    { id: 12, titre: 'Bose QuietComfort 45', description: 'Casque antibruit haut de gamme de Bose', prix: 329 },
    { id: 13, titre: 'Nintendo Switch OLED', description: 'Console de jeu Nintendo avec écran OLED', prix: 349 },
    { id: 14, titre: 'Dyson V11 Absolute', description: 'Aspirateur balai sans fil haut de gamme', prix: 599 },
    { id: 15, titre: 'Google Pixel 6 Pro', description: 'Smartphone phare de Google avec caméra avancée', prix: 899 },
    { id: 16, titre: 'Garmin Fenix 7X Sapphire', description: 'Montre de sport GPS haut de gamme', prix: 899 },
    { id: 17, titre: 'LG OLED C1', description: 'Téléviseur OLED 4K haut de gamme de LG', prix: 1999 },
    { id: 18, titre: 'Microsoft Surface Laptop Studio', description: 'Ordinateur portable convertible de Microsoft', prix: 1599 },
    { id: 19, titre: 'DJI Mavic 3', description: 'Drone de photographie et de vidéographie professionnel', prix: 1999 },
    { id: 20, titre: 'Segway Ninebot MAX G30LP', description: 'Trottinette électrique à grande autonomie', prix: 799 },
  ];
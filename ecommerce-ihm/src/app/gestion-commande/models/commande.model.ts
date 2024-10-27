import { Produit } from "./produit.model"
import { Page } from '../../shared/models/page.model';


export interface Commande {
  firstName?: string;
  lastName?: string; 
  email?: string;
  phone?: string;
  username?: string;
  password?: string;
  ligneCommandes?: {quantite: number, produitId: number}[];
}


export interface CommandeForm {
    userInfo: {
      firstName: string,
      lastName: string
    },
    contactPreference: string,
    email?: {
      email: string,
      confirmEmail: string
    },
    phone?: string,
    loginInfo: {
      username: string,
      password: string,
      confirmPassword: string
    },
    ligneCommandes?: {quantite: number, produitId: number}[]
  
}


export const page_g : Page<Commande> =  {
  content: [],
  totalElements: 18,
  totalPages: 3,
  last: false,
  size: 6,
  numberOfElements: 3,
};

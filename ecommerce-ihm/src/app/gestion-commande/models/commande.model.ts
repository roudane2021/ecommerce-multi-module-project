import { Produit } from "./produit.model"
import { Page } from '../../shared/models/page.model';

export interface Commande {
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
    produits?: Produit[]
  
}


export const page_g : Page<Commande> =  {
  content: [],
  totalElements: 18,
  totalPages: 3,
  last: false,
  size: 6,
  numberOfElements: 3,
};

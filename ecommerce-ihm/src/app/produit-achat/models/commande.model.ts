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

  export interface Produit{
    produitID: number,
    qte: number
  }
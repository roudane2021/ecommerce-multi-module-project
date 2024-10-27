import { environment } from "src/environments/environment";

export const wsService = {
    'produit': {
        'searchProduit': `${environment.prefixApiProduit}/produits/searchProduit`,
        'produitByID': `${environment.prefixApiProduit}/produits/`
    },
    'commande': {
        'createCommande': `${environment.prefixApiCommande}/commandes`,
        'searchProduit': `${environment.prefixApiCommande}/commandes/search-commande`,
    },
    'paiement': {}
};
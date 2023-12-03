CREATE TABLE paiement (
    id NUMBER,
    id_commande NUMBER UNIQUE,
    montant NUMBER,
    numero_carte NUMBER,
    CONSTRAINT pk_paiement_id PRIMARY KEY (id)
);
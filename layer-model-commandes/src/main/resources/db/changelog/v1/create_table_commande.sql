
CREATE TABLE commande (
    id NUMBER ,
    productId NUMBER,
    dateCommande TIMESTAMP,
    quantite NUMBER,
    commandePayee NUMBER(1),
    CONSTRAINT pk_commande_id PRIMARY KEY (id)
);
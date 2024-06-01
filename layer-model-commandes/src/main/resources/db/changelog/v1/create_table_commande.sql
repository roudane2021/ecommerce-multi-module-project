
CREATE TABLE commande (
    id NUMBER ,
    productId NUMBER,
    date_commande TIMESTAMP,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    quantite NUMBER,
    commandePayee NUMBER(1),
    CONSTRAINT pk_commande_id PRIMARY KEY (id)
);
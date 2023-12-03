CREATE TABLE produit (
    id NUMBER,
    titre VARCHAR2(255 CHAR),
    description VARCHAR2(1000 CHAR),
    image VARCHAR2(1000 CHAR),
    prix NUMBER(10, 2),
    CONSTRAINT pk_produit_id PRIMARY KEY (id)
);
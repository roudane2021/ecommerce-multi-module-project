-- drop les columns suivant : productid quantite commandepayee
-- Supprimer les colonnes
ALTER TABLE commande DROP COLUMN productid;
ALTER TABLE commande DROP COLUMN quantite;
ALTER TABLE commande DROP COLUMN commandepayee;

-- Ajouter les nouvelles colonnes
ALTER TABLE commande ADD first_name VARCHAR2(30 CHAR);
ALTER TABLE commande ADD last_name VARCHAR2(30 CHAR);
ALTER TABLE commande ADD status NUMBER(2);
ALTER TABLE commande ADD email VARCHAR2(50 CHAR);
ALTER TABLE commande ADD phone VARCHAR2(30 CHAR);
ALTER TABLE commande ADD username VARCHAR2(30 CHAR);
ALTER TABLE commande ADD password VARCHAR2(50 CHAR);


-- table enseignant
CREATE TABLE ligne_commande (
id NUMBER,
quantite NUMBER,
produit_id NUMBER,
created_at TIMESTAMP,
updated_at TIMESTAMP,
commande_id NUMBER,
CONSTRAINT pk_ligne_commande PRIMARY KEY (id),
CONSTRAINT fk_ligne_commande_commande FOREIGN KEY (commande_id) REFERENCES commande(id)
);

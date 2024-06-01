import { Commande } from '../models/commande.model';


export const commandes: Commande[] = [
    {
      userInfo: { firstName: 'John', lastName: 'Doe' },
      contactPreference: 'email',
      email: { email: 'john@example.com', confirmEmail: 'john@example.com' },
      loginInfo: { username: 'johndoe', password: 'password', confirmPassword: 'password' },
      produits: [
        { id: 1, titre: 'Produit 1', description: 'Description du produit 1', prix: 10.99 },
        { id: 2, titre: 'Produit 2', description: 'Description du produit 2', prix: 20.49 }
      ]
    },
    {
      userInfo: { firstName: 'Rachid', lastName: 'Roudane' },
      contactPreference: 'email',
      email: { email: 'rachid@example.com', confirmEmail: 'rachid@example.com' },
      loginInfo: { username: 'rachid', password: '123456', confirmPassword: 'password' },
      produits: [
        { id: 1, titre: 'Produit 1', description: 'Description du produit 1', prix: 10.99 },
        { id: 2, titre: 'Produit 2', description: 'Description du produit 2', prix: 20.49 }
      ]
    },
    {
      userInfo: { firstName: 'Alice', lastName: 'Smith' },
      contactPreference: 'phone',
      phone: '1234567890',
      loginInfo: { username: 'alicesmith', password: 'password', confirmPassword: 'password' },
      produits: [
        { id: 3, titre: 'Produit 3', description: 'Description du produit 3', prix: 15.75 },
        { id: 4, titre: 'Produit 4', description: 'Description du produit 4', prix: 8.99 }
      ]
    },
    {
      userInfo: { firstName: 'Bob', lastName: 'Johnson' },
      contactPreference: 'email',
      email: { email: 'bob@example.com', confirmEmail: 'bob@example.com' },
      loginInfo: { username: 'bobjohnson', password: 'password', confirmPassword: 'password' },
      produits: [
        { id: 5, titre: 'Produit 5', description: 'Description du produit 5', prix: 12.25 },
        { id: 6, titre: 'Produit 6', description: 'Description du produit 6', prix: 30.99 }
      ]
    },
    {
      userInfo: { firstName: 'Emma', lastName: 'Brown' },
      contactPreference: 'phone',
      phone: '9876543210',
      loginInfo: { username: 'emmabrown', password: 'password', confirmPassword: 'password' },
      produits: [
        { id: 7, titre: 'Produit 7', description: 'Description du produit 7', prix: 25.99 },
        { id: 8, titre: 'Produit 8', description: 'Description du produit 8', prix: 18.49 }
      ]
    },
    {
      userInfo: { firstName: 'Michael', lastName: 'Wilson' },
      contactPreference: 'email',
      email: { email: 'michael@example.com', confirmEmail: 'michael@example.com' },
      loginInfo: { username: 'michaelwilson', password: 'password', confirmPassword: 'password' },
      produits: [
        { id: 9, titre: 'Produit 9', description: 'Description du produit 9', prix: 9.99 },
        { id: 10, titre: 'Produit 10', description: 'Description du produit 10', prix: 16.75 }
      ]
    },
    {
        userInfo: { firstName: 'John_2', lastName: 'Doe_2' },
        contactPreference: 'email',
        email: { email: 'john@example.com', confirmEmail: 'john@example.com' },
        loginInfo: { username: 'johndoe', password: 'password', confirmPassword: 'password' },
        produits: [
          { id: 1, titre: 'Produit 1', description: 'Description du produit 1', prix: 10.99 },
          { id: 2, titre: 'Produit 2', description: 'Description du produit 2', prix: 20.49 }
        ]
      },
      {
        userInfo: { firstName: 'Rachid_2', lastName: 'Roudane_2' },
        contactPreference: 'email',
        email: { email: 'rachid@example.com', confirmEmail: 'rachid@example.com' },
        loginInfo: { username: 'rachid', password: '123456', confirmPassword: 'password' },
        produits: [
          { id: 1, titre: 'Produit 1', description: 'Description du produit 1', prix: 10.99 },
          { id: 2, titre: 'Produit 2', description: 'Description du produit 2', prix: 20.49 }
        ]
      },
      {
        userInfo: { firstName: 'Alice_2', lastName: 'Smith_2' },
        contactPreference: 'phone',
        phone: '1234567890',
        loginInfo: { username: 'alicesmith', password: 'password', confirmPassword: 'password' },
        produits: [
          { id: 3, titre: 'Produit 3', description: 'Description du produit 3', prix: 15.75 },
          { id: 4, titre: 'Produit 4', description: 'Description du produit 4', prix: 8.99 }
        ]
      },
      {
        userInfo: { firstName: 'Bob_2', lastName: 'Johnson_2' },
        contactPreference: 'email',
        email: { email: 'bob@example.com', confirmEmail: 'bob@example.com' },
        loginInfo: { username: 'bobjohnson', password: 'password', confirmPassword: 'password' },
        produits: [
          { id: 5, titre: 'Produit 5', description: 'Description du produit 5', prix: 12.25 },
          { id: 6, titre: 'Produit 6', description: 'Description du produit 6', prix: 30.99 }
        ]
      },
      {
        userInfo: { firstName: 'Emma_2', lastName: 'Brown_2' },
        contactPreference: 'phone',
        phone: '9876543210',
        loginInfo: { username: 'emmabrown', password: 'password', confirmPassword: 'password' },
        produits: [
          { id: 7, titre: 'Produit 7', description: 'Description du produit 7', prix: 25.99 },
          { id: 8, titre: 'Produit 8', description: 'Description du produit 8', prix: 18.49 }
        ]
      },
      {
        userInfo: { firstName: 'Michael_2', lastName: 'Wilson_2' },
        contactPreference: 'email',
        email: { email: 'michael@example.com', confirmEmail: 'michael@example.com' },
        loginInfo: { username: 'michaelwilson', password: 'password', confirmPassword: 'password' },
        produits: [
          { id: 9, titre: 'Produit 9', description: 'Description du produit 9', prix: 9.99 },
          { id: 10, titre: 'Produit 10', description: 'Description du produit 10', prix: 16.75 }
        ]
    },
      
    {
        userInfo: { firstName: 'John_3', lastName: 'Doe_3' },
        contactPreference: 'email',
        email: { email: 'john@example.com', confirmEmail: 'john@example.com' },
        loginInfo: { username: 'johndoe', password: 'password', confirmPassword: 'password' },
        produits: [
          { id: 1, titre: 'Produit 1', description: 'Description du produit 1', prix: 10.99 },
          { id: 2, titre: 'Produit 2', description: 'Description du produit 2', prix: 20.49 }
        ]
      },
      {
        userInfo: { firstName: 'Rachid_3', lastName: 'Roudane_3' },
        contactPreference: 'email',
        email: { email: 'rachid@example.com', confirmEmail: 'rachid@example.com' },
        loginInfo: { username: 'rachid', password: '123456', confirmPassword: 'password' },
        produits: [
          { id: 1, titre: 'Produit 1', description: 'Description du produit 1', prix: 10.99 },
          { id: 2, titre: 'Produit 2', description: 'Description du produit 2', prix: 20.49 }
        ]
      },
      {
        userInfo: { firstName: 'Alice_3', lastName: 'Smith_3' },
        contactPreference: 'phone',
        phone: '1234567890',
        loginInfo: { username: 'alicesmith', password: 'password', confirmPassword: 'password' },
        produits: [
          { id: 3, titre: 'Produit 3', description: 'Description du produit 3', prix: 15.75 },
          { id: 4, titre: 'Produit 4', description: 'Description du produit 4', prix: 8.99 }
        ]
      },
      {
        userInfo: { firstName: 'Bob_3', lastName: 'Johnson_3' },
        contactPreference: 'email',
        email: { email: 'bob@example.com', confirmEmail: 'bob@example.com' },
        loginInfo: { username: 'bobjohnson', password: 'password', confirmPassword: 'password' },
        produits: [
          { id: 5, titre: 'Produit 5', description: 'Description du produit 5', prix: 12.25 },
          { id: 6, titre: 'Produit 6', description: 'Description du produit 6', prix: 30.99 }
        ]
      },
      {
        userInfo: { firstName: 'Emma_3', lastName: 'Brown_3' },
        contactPreference: 'phone',
        phone: '9876543210',
        loginInfo: { username: 'emmabrown', password: 'password', confirmPassword: 'password' },
        produits: [
          { id: 7, titre: 'Produit 7', description: 'Description du produit 7', prix: 25.99 },
          { id: 8, titre: 'Produit 8', description: 'Description du produit 8', prix: 18.49 }
        ]
      },
      {
        userInfo: { firstName: 'Michael_3', lastName: 'Wilson_3' },
        contactPreference: 'email',
        email: { email: 'michael@example.com', confirmEmail: 'michael@example.com' },
        loginInfo: { username: 'michaelwilson', password: 'password', confirmPassword: 'password' },
        produits: [
          { id: 9, titre: 'Produit 9', description: 'Description du produit 9', prix: 9.99 },
          { id: 10, titre: 'Produit 10', description: 'Description du produit 10', prix: 16.75 }
        ]
      },
    
  
];
  
export const readCommandes = (indiceDebut: number, nombreElements: number = 6): Commande[] => {

    // Vérifier si l'indice de début est valide
    if (indiceDebut < 0 || indiceDebut >= commandes.length) {
      throw new Error('L\'indice de début est invalide.');
    }
  const page: number = indiceDebut * nombreElements;

  return commandes.slice(page, page + nombreElements);
}
  
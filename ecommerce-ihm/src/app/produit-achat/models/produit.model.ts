
  
  export interface Produit {
    id?: number
    titre?: string
    description?: string
    image?: string
    prix?: number
  }


  export class Criteria {
    name!: string;
    value?: any;
    listValue?: Array<any>;
    operator!: Operator;
}

export enum Operator {
  EQUALS,
  NOT_EQUALS,
  LESS_THAN,
  LESS_OR_EQUAL,
  GREATER_THAN,
  GREATER_OR_EQUAL,
  IN,
  NOT_IN,
  LIKE
}

export const OperatorOptions = [
  { key: Operator.EQUALS, value: 'Egalé' },
  { key: Operator.NOT_EQUALS, value: 'Pas égalé' },
  { key: Operator.LIKE, value: 'Like' }
];

export enum ActionP {
  DETAILS,
  PANIER
}

export  interface ResponseEvent{
  event:ActionP,
  idP: number
}

export class User {
id?:number
}
  
  
export interface Page {
    content?: Produit[]
    pageable?: Pageable
    totalElements?: number
    totalPages?: number
    last?: boolean
    number?: number
    sort?: Sort
    size?: number
    numberOfElements?: number
    first?: boolean
    empty?: boolean
  }
  
  export interface Produit {
    id?: number
    titre?: string
    description?: string
    image?: string
    prix?: number
  }
  
  export interface Pageable {
    sort?: Sort
    offset?: number
    pageNumber?: number
    pageSize?: number
    paged?: boolean
    unpaged?: boolean
  }
  
  export interface Sort {
    empty?: boolean
    sorted?: boolean
    unsorted?: boolean
  }
  
  
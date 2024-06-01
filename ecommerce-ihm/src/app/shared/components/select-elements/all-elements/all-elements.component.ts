import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-all-elements',
  templateUrl: './all-elements.component.html',
  styleUrls: ['./all-elements.component.scss']
})
export class AllElementsComponent {

  @Input() elements!: string[];
  selectedElements: string[] = []; 

  toggleSelection(element: string) {
    // Vérifie si l'élément est déjà sélectionné
    const isSelected = this.selectedElements?.includes(element) || false;

    // Si l'élément est déjà sélectionné
    if (isSelected) {
        // Utilise la méthode filter pour créer un nouveau tableau
        // qui exclut l'élément sélectionné
        this.selectedElements = this.selectedElements.filter(item => item !== element);
    } else {
        // Si l'élément n'est pas déjà sélectionné
        // Utilise le spread operator (...) pour créer une nouvelle copie du tableau selectedElements
        // Ajoute l'élément à la nouvelle copie du tableau
        this.selectedElements = [...this.selectedElements, element];
    }

}


  }

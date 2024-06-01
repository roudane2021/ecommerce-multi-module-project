import { ChangeDetectionStrategy, Component, EventEmitter, Input, Output } from '@angular/core';
import { Commande } from '../../../gestion-commande/models/commande.model';
import { EventType, EventUser } from '../../models/event.model';


@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class TableComponent {

  
  displayedColumns: string[] = ['userInfo.lastName', 'userInfo.firstName', 'email.email', 'phone', 'Action'];
  @Input() content!: Commande[];
  @Output() senEvent: EventEmitter<EventUser> = new EventEmitter<EventUser>();

  getSecondPart = (column: string): string  => {
    const columnParts: string[] = column?.split('.');

      return columnParts &&  (columnParts[columnParts.length - 1] ?? ''); 

  }

  getNestedValue = (row: any, column: string): any => {
    // Si la colonne est "owner.name", on divise en utilisant "."
    const columnParts = column.split('.');
    let value = row;
  
    // Utilisation de la méthode reduce pour accéder aux propriétés imbriquées
    return columnParts.reduce((acc, cur) => acc && acc[cur], value);
  }

  // Méthode appelée lors du clic sur le bouton "Supprimer"
  delete = (element: any): void => {
    const eventDelete: EventUser = { type: EventType.DELETE, element };
    this.senEvent.emit(eventDelete);
  }

  // Méthode appelée lors du clic sur le bouton "Modifier"
  edit = (element: any): void => {
    const eventUpdate: EventUser = { type: EventType.UPDATE, element };
    this.senEvent.emit(eventUpdate);
  }
}

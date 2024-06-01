import { Component, EventEmitter, Input, Output, ViewChild } from '@angular/core';
import { AllElementsComponent } from './all-elements/all-elements.component';
import { SelectElementComponent } from './select-element/select-element.component';

@Component({
  selector: 'app-select-elements',
  templateUrl: './select-elements.component.html',
  styleUrls: ['./select-elements.component.scss']
})
export class SelectElementsComponent {



  @Input() elementsAll!: string[];
           elementsSelected: string[] = [];
  @Output() sendElementSelected : EventEmitter<string[]> = new EventEmitter<string[]>();

  @ViewChild(SelectElementComponent) selectElementComponent! : SelectElementComponent;
  @ViewChild(AllElementsComponent) allElementsComponent! : AllElementsComponent;


  removeAllElements() {
    this.elementsAll = [...this.elementsAll, ...this.elementsSelected];
    this.elementsSelected = [];
    this.initElementSelected();
          }
  removeElements() {
    const elementSelect : string[] = this.selectElementComponent?.selectedElements || [];

    this.elementsAll = [...this.elementsAll, ...elementSelect];
    this.elementsSelected = this.elementsSelected.filter(element => !elementSelect.includes(element));
    this.initElementSelected();

          }
  addAllElements() {
    this.elementsSelected = [...this.elementsSelected, ...this.elementsAll];
    this.elementsAll = [];
    this.initElementSelected();   
          }
          
  addElements() {
    const elementSelect : string[] = this.allElementsComponent?.selectedElements || [];

    this.elementsSelected = [...this.elementsSelected, ...elementSelect];
    this.elementsAll = this.elementsAll.filter(element => !elementSelect.includes(element));
    this.initElementSelected();
          }
          
          
  envoyer() {
            const elementSelected = [...this.elementsSelected];
            this.sendElementSelected.emit(elementSelected);

            this.removeAllElements();
            
                }
    

  initElementSelected(): void {
    this.selectElementComponent.selectedElements = [];
    this.allElementsComponent.selectedElements = [];
          }


}

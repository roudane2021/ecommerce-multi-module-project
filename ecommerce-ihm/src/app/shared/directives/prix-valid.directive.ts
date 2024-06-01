import { Directive, ElementRef, HostListener, inject } from '@angular/core';
import { NgControl } from '@angular/forms';

@Directive({
  selector: '[appPrixValid]'
})
export class PrixValidDirective {

  private el: ElementRef = inject(ElementRef);
  private ngControl: NgControl = inject(NgControl);

  @HostListener('input')
  onInput():void {
    const inputValue = this.el.nativeElement.value;

     // Vérifie si l'entrée est un nombre valide et n'est pas négative ni égale à zéro
    if( !this.isValidPrice(inputValue)){
      this.ngControl.reset();
    }
  }

  private isValidPrice(value: string): boolean {
    // Vérifie si la valeur est composée uniquement de chiffres et n'est pas négative ni égale à zéro
    return /^\d+$/.test(value) && parseInt(value, 10) > 0;
  }


}

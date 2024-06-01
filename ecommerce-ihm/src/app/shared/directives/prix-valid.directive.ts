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
    // Vérifie si la valeur est un nombre décimal ou entier positif supérieur à zéro
    return /^[0-9]+(\.[0-9]+)?$/.test(value) && parseFloat(value) > 0;
}


}

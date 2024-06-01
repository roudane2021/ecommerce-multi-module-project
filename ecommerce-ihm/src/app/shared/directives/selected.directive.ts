import { AfterViewInit, Directive, ElementRef, HostListener, Input, Renderer2, inject } from "@angular/core";

@Directive({
    selector: '[selected]'
})
export class SelectedDirective {

    private el : ElementRef = inject(ElementRef);
    private renderer: Renderer2 = inject(Renderer2);
    private isSelected: boolean = false;

   

    @HostListener('click')
    onClick(): void {
        this.isSelected = !this.isSelected; // Inverse le statut de sélection
        this.updateBackground(); // Met à jour le fond en fonction de la sélection
    }

    private updateBackground(): void {
        if (this.isSelected) {
            this.renderer.setStyle(this.el.nativeElement, 'background-color', 'yellow');
        } else {
            this.renderer.setStyle(this.el.nativeElement, 'background-color', 'white');
        }
    }

}
import { AfterViewInit, Directive, ElementRef, HostListener, Input, Renderer2, inject } from "@angular/core";

@Directive({
    selector: '[shorten]'
})
export class ShortenDirective implements AfterViewInit{

    private el : ElementRef = inject(ElementRef);
    private renderer: Renderer2 = inject(Renderer2);

    @Input() size : number = 2;
    @Input() value! : string;

    ngAfterViewInit(): void {
        this.updateText();
    }

    @HostListener('click')
    onClick(): void {
        this.updateText();
    }

    private updateText(): void {
        const valueInit = this.value?.trim() || '';
        const valueCurrent = this.el?.nativeElement?.textContent?.trim() || '';
        if( valueInit === valueCurrent) {
            this.renderer.setProperty(this.el.nativeElement, 'textContent', valueCurrent.substring(0, this.size) + '...');
        }else {
            this.renderer.setProperty(this.el.nativeElement, 'textContent', valueInit);  
        }

    }

}
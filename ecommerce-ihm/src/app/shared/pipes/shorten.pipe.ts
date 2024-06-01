import { PipeTransform, Pipe } from "@angular/core";


@Pipe({
    name: 'shorten'
})
export class ShortenPipe implements PipeTransform {
    transform(value: string, maxLength : number =10): string {
        
        return value && value.length > maxLength ? value.substring(0, maxLength)+'...' : value;
    }

}
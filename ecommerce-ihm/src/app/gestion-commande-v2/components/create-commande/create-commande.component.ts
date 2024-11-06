import { Component, OnInit } from '@angular/core';
import { Observable, Subscription } from 'rxjs';

@Component({
  selector: 'app-create-commande',
  templateUrl: './create-commande.component.html',
  styleUrls: ['./create-commande.component.scss']
})
export class CreateCommandeComponent implements OnInit{

  sub!: Subscription;

  ngOnInit(): void {
    const obs: Observable<number> = new Observable((subscribe) => {
      subscribe.next(1);
      subscribe.next(3);
      subscribe.next(5);
      setTimeout(() => {
        subscribe.next(7);
        subscribe.complete();
      }, 2000);
    });

    this.sub = obs.subscribe({
      next: (data) => { console.log(data) },
      complete: () => { console.log(`complete !!!!`)}
    });

    

  }

  

}

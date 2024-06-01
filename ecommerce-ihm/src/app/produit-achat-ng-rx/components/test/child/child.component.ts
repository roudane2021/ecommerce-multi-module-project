import { ChangeDetectionStrategy, ChangeDetectorRef, Component, DoCheck, Input, NgZone, OnChanges, OnInit, SimpleChanges, inject } from '@angular/core';
import { Telephone } from '../parent/parent.component';

@Component({
  selector: 'app-child',
  templateUrl: './child.component.html',
  styleUrls: ['./child.component.scss'],
 changeDetection: ChangeDetectionStrategy.OnPush
})
export class ChildComponent implements OnInit, OnChanges, DoCheck{

  @Input() tele! : Telephone;
  changesDecRef: ChangeDetectorRef = inject(ChangeDetectorRef);
  zone: NgZone = inject(NgZone);




  ngOnChanges(changes: SimpleChanges): void {
    console.log(` change : ${changes}`);
    console.table(changes);
  }
  ngOnInit(): void {
    console.log('ngOnInit child!!!');
  }

  ngDoCheck(): void {
    console.log('Do check child**********************'+this.name);
  }

  updateTeleChild() {
  this.changesDecRef.detectChanges();
  this.changesDecRef.markForCheck();
    }

    name = 'John';

  changeName() {

      setTimeout(() => this.name = this.name +1, 100);
      this.changesDecRef.markForCheck();


  }

  test() {
    console.log('Test Child View !!!');
  }

}

import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Task} from '../Task';

@Component({
  selector: 'app-task',
  imports: [],
  standalone: true,
  templateUrl: './task.component.html',
  styleUrl: './task.component.scss'
})
export class TaskComponent {
  @Input() task!: Task;
  @Output() deleteTask = new EventEmitter<number>();

  onDelete() {
    this.deleteTask.emit(this.task.id);
  }
}

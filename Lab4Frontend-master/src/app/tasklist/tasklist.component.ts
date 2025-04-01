import {Component, OnInit} from '@angular/core';
import {Task} from '../Task';
import {TaskService} from '../Task.service';
import {ActivatedRoute, Router} from '@angular/router';
import {TaskComponent} from '../task/task.component';
import {NgFor} from '@angular/common';


@Component({
  selector: 'app-tasklist',
  imports: [
    TaskComponent, NgFor
  ],
  standalone: true,
  templateUrl: './tasklist.component.html',
  styleUrl: './tasklist.component.scss'
})
export class TasklistComponent implements OnInit{
  tasks: Task[] = [];
  projectId!: string;

  constructor(private taskService: TaskService, private router: Router, private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      const id = params.get('id');
      if(id) {
        this.projectId = id;
        this.loadTasks();
      }
    })
  }
  loadTasks()
  {
    this.taskService.getTasks(this.projectId).subscribe((tasks) => this.tasks = tasks);
  }
  deleteTask(taskId:number){
    this.taskService.deleteTask(this.projectId, taskId).subscribe({
        next: () => this.loadTasks()
      }
    );
  }
}

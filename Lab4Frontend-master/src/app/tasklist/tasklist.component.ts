import {Component, OnInit} from '@angular/core';
import {Task} from '../Task';
import {TaskService} from '../Task.service';
import {Router} from '@angular/router';
import {ProjectDataService} from '../project-data';
import {Project} from '../Project';
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
  project: Project;

  constructor(private taskService: TaskService, private router: Router, private projectData: ProjectDataService) {
    this.project = this.projectData.getProject();
  }

  ngOnInit() {

    this.loadTasks();
  }
  loadTasks()
  {
    this.taskService.getTasks(this.project.id).subscribe((tasks) => this.tasks = tasks);
  }
  deleteTask(taskId:number){
    this.taskService.deleteTask(this.project.id, taskId).subscribe();
    this.taskService.getTasks(this.project.id).subscribe((tasks) => this.tasks = tasks);
  }
}

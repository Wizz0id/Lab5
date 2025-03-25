import {Component, OnInit} from '@angular/core';
import {Project} from '../Project';
import {ProjectService} from '../Project.service';
import {TaskService} from '../Task.service';
import {CommonModule, NgForOf} from '@angular/common';
import {FormControl, FormsModule, ReactiveFormsModule} from '@angular/forms';
import {Router} from '@angular/router';
import {ProjectComponent} from '../project/project.component';

@Component({
  selector: 'app-projectlist',
  imports: [NgForOf, CommonModule, FormsModule, ReactiveFormsModule, ProjectComponent],
  templateUrl: './projectlist.component.html',
  standalone: true,
  styleUrl: './projectlist.component.scss'
})
export class ProjectlistComponent implements OnInit {
  projects: Project[] = [];
  uncompletedTasks: Map<number, number> = new Map<number, number>();

  searchQuery: FormControl = new FormControl("");

  constructor(private projectService: ProjectService, private taskService: TaskService, private router: Router) { //, private datePipe: DatePipe
  }

  ngOnInit(): void {
    this.loadProjectsAndTasks();
  }

  loadProjectsAndTasks(): void {
    this.projectService.loadProjectsAndTasks(this.searchQuery.value).subscribe(([projects, uncompletedTasks]) => {
      this.projects = projects;
      this.uncompletedTasks = uncompletedTasks;
    });
  }

  onSearchKeyDown(event: Event): void {
    const keyboardEvent = event as KeyboardEvent;
    if (keyboardEvent.key === 'Enter') {
      this.loadProjectsAndTasks();
    }
  }

  // formatDate(date: string): string {
  //   return <string>this.datePipe.transform(date, 'dd.MM.yyyy');
  // }

  navigateToCreateProject(): void {
    this.router.navigate(['/projects/new']);
  }
}

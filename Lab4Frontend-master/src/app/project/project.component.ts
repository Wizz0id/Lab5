import {Component, EventEmitter, Input, numberAttribute, Output} from '@angular/core';
import {Project} from '../Project';
import {Router} from '@angular/router';
import {ProjectDataService} from '../project-data';
import {DatePipe} from '@angular/common';

@Component({
  selector: 'app-project',
  imports: [
    DatePipe
  ],
  templateUrl: './project.component.html',
  standalone: true,
  styleUrl: './project.component.scss'
})
export class ProjectComponent {
  @Input() project!: Project;
  @Input({transform: numberAttribute}) count!: number;
  @Output() event = new EventEmitter<number>()


  constructor(private router: Router, private projectData: ProjectDataService) {
  }

  editProject(): void
  {
    this.router.navigate([`/projects/${this.project.id}`])
  }

  getTasksByProjectId()
  {
    this.projectData.setProject(this.project);
    this.router.navigate([`/projects/${this.project.id}/tasks`]);
  }
}

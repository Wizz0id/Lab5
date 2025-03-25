import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ProjectDataService {
  private project: any;

  setProject(project: any) {
    this.project = project;
  }

  getProject() {
    return this.project;
  }
}

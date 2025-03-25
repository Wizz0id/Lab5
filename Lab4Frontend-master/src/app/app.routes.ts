import { Routes } from '@angular/router';
import { TasklistComponent } from './tasklist/tasklist.component';
import { ProjectlistComponent } from './projectlist/projectlist.component';
import { NewProjectComponent } from './newProject/newProject.component';

export const routes: Routes = [
  {path: 'projects', component: ProjectlistComponent},
  {path: 'projects/new', component: NewProjectComponent},
  {path: 'projects/:id', component: NewProjectComponent},
  {path: 'projects/:id/tasks', component: TasklistComponent}
];

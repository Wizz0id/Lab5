import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {catchError, forkJoin, map, Observable, Subscription, throwError} from 'rxjs';
import {Project} from './Project';
import {environment} from './enviroment';
import {UncomplitedTasks} from './UncomplitedTasks';


@Injectable({
  providedIn: 'root'
})
export class ProjectService {
  private projectUrl = `${environment.apiUrl}projects/api/v1`;

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) {
  }

  getProjects(search?: string): Observable<Project[]> {
    const url = search ? `${this.projectUrl}?search=${search}` : this.projectUrl;
    return this.http.get<Project[]>(url)
      .pipe(
        catchError(this.handleError<Project[]>('getProjects', []))
      );
  }

  loadProjectsAndTasks(search?: string): Observable<[Project[], Map<number, number>]> {
    return forkJoin([
      this.getProjects(search),
      this.getUncompletedTasks()
    ]);
  }

  getUncompletedTasks(): Observable<Map<number, number>> {
    return this.http.get<UncomplitedTasks[]>(`${this.projectUrl}/uncompleted`)
      .pipe(
        map(projects => {
          const projMap = new Map<number, number>();
          projects.forEach(proj => {
            projMap.set(proj.id, proj.count);
          });return projMap;}), catchError(this.handleError<Map<number, number>>('getUncompletedTasks', new Map())));
  }


  getProjectById(id: number): Observable<Project> {
    const url = `${this.projectUrl}/${id}`;
    return this.http.get<Project>(url).pipe(catchError(this.handleError<Project>(`getProjectById id=${id}`)));
  }

  updateProject(id: number, project: Project): Observable<Project> {
    const url = `${this.projectUrl}/${id}`;
    return this.http.put<Project>(url, project, this.httpOptions).pipe(catchError(this.handleError<Project>('updateProject')));
  }

  createProject(project: Project): Observable<Project>{
    const url = `${this.projectUrl}/new`;
    return this.http.post<Project>(url, project, this.httpOptions).pipe(catchError(this.handleError<Project>('createProject')));
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(`${operation} failed: ${error.message}`);
      return throwError(error);
    };
  }
}

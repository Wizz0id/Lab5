import {Injectable} from '@angular/core';
import {environment} from './enviroment';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {catchError, Observable, throwError} from 'rxjs';
import {Task} from './Task';


@Injectable({
  providedIn: 'root'
})
export class TaskService {
  private taskUrl = `${environment.apiUrl}projects/api/v1`;

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  }

  constructor(private http: HttpClient) {
  }

  getTasks(projectId: number): Observable<Task[]> {
    const url = `${this.taskUrl}/${projectId}/tasks`;
    return this.http.get<Task[]>(url)
      .pipe(
        catchError(this.handleError<Task[]>('getTasks', []))
      );
  }

  deleteTask(projectId: number, taskId: number): Observable<void> {
    const url = `${this.taskUrl}/${projectId}/tasks/${taskId}`;
    return this.http.delete<void>(url).pipe(catchError(this.handleError<void>('deleteTaskById')));
  }

  getTaskById(projectId: number, taskId: number): Observable<Task> {
    const url = `${this.taskUrl}/${projectId}/tasks/${taskId}`;
    return this.http.get<Task>(url)
      .pipe(
        catchError(this.handleError<Task>(`getTask id=${taskId}`))
      );
  }

  createTask(projectId: number, task: Task): Observable<Task> {
    const url = `${this.taskUrl}/${projectId}/tasks`;
    return this.http.post<Task>(url, task, this.httpOptions)
      .pipe(
        catchError(this.handleError<Task>('createTask'))
      );
  }

  updateTask(projectId: number, taskId: number, task: Task): Observable<Task> {
    const url = `${this.taskUrl}/${projectId}/tasks/${taskId}`;
    return this.http.put<Task>(url, task, this.httpOptions)
      .pipe(
        catchError(this.handleError<Task>('updateTask'))
      );
  }

  deleteCompletedTasks(projectId: number): Observable<void> {
    const url = `${this.taskUrl}/${projectId}/tasks`;
    return this.http.delete<void>(url)
      .pipe(
        catchError(this.handleError<void>('deleteCompletedTasks'))
      );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(`${operation} failed: ${error.message}`);
      return throwError(error);
    };
  }
}

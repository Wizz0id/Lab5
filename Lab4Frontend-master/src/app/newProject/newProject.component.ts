import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule} from '@angular/forms';
import {CommonModule} from '@angular/common';
import {ActivatedRoute, Router} from '@angular/router';
import {ProjectService} from '../Project.service';

@Component({
  selector: 'app-new-project',
  imports: [ ReactiveFormsModule, CommonModule, FormsModule],
  standalone: true,
  templateUrl: './newProject.component.html',
  styleUrl: './newProject.component.scss'
})
export class NewProjectComponent implements OnInit {

  projectForm: FormGroup;
  projectId: number;

  constructor(private projectService: ProjectService, private fb: FormBuilder, private route: ActivatedRoute, private router: Router) {
    this.projectForm = this.fb.group({
      name: [""],
      description: [""],
      startDate: [""],
      endDate: [""]
    });
    this.projectId = 0;
  }

  ngOnInit():void {
    const id = this.route.snapshot.paramMap.get('id')
    if(id)
    {
      this.projectId = Number(id);
      this.loadProject();
    }
  }

  loadProject(): void{
    this.projectService.getProjectById(this.projectId).subscribe(
      (projectData) => {
        this.projectForm.patchValue({
          name: projectData.name,
          description: projectData.description,
          startDate: projectData.startDate,
          endDate: projectData.endDate
        });
      }
    );
  }
  onSubmit(){
    if(this.projectForm.valid)
    {
      const projectData = this.projectForm.value;
      if(this.projectId != 0)
      {
        this.projectService.updateProject(this.projectId, projectData).subscribe(projectData);
      }
      else{
        this.projectService.createProject(projectData).subscribe(projectData);
      }
      this.router.navigate(["/projects"]);
    }
  }
}

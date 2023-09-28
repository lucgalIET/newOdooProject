import { Component } from '@angular/core';

import { CdkDragDrop, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';
import { Project } from 'src/app/models/project.model';
import { Column } from 'src/app/models/column.model';

@Component({
  selector: 'app-admin-proj-activities',
  templateUrl: './admin-proj-activities.component.html',
  styleUrls: ['./admin-proj-activities.component.css']
})
export class AdminProjActivitiesComponent {

  projects: Project = new Project([
    new Column('Analisi', [
      'progetto 1',
      'progetto 2',
      'progetto 3',
      'progetto 4'
    ]),
    new Column('Specifiche', [
      'progetto 5',
      'progetto 6',
      'progetto 7',
      'progetto 8'
    ]),
    new Column('Design', [
      'progetto 9',
      'progetto 10',
      'progetto 11',
      'progetto 12'
    ]),
    new Column('Sviluppo', [
      'progetto 13',
      'progetto 14',
      'progetto 15',
      'progetto 16'
    ]),
    new Column('Testing', [
      'progetto 17',
      'progetto 18',
      'progetto 19',
      'progetto 20'
    ])
  ])

  drop(event: CdkDragDrop<string[]>) {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      transferArrayItem(
        event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex,
      );
    }
  }
}
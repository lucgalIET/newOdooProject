import { Component } from '@angular/core';

import { CdkDragDrop, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';
import { Project } from 'src/app/models/project.model';
import { Column } from 'src/app/models/column.model';

@Component({
  selector: 'app-admin-projects',
  templateUrl: './admin-projects.component.html',
  styleUrls: ['./admin-projects.component.css']
})
export class AdminProjectsComponent {

  projects: Project = new Project([
    new Column('Non definitiva', [
      'progetto 1',
      'progetto 2',
      'progetto 3',
      'progetto 4'
    ]),
    new Column('Progetti interni', [
      'progetto 5',
      'progetto 6',
      'progetto 7',
      'progetto 8'
    ]),
    new Column('HR', [
      'progetto 9',
      'progetto 10',
      'progetto 11',
      'progetto 12'
    ]),
    new Column('Spagna', [
      'progetto 13',
      'progetto 14',
      'progetto 15',
      'progetto 16'
    ]),
    new Column('Global', [
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

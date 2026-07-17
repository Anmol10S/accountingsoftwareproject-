import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

import { JournalEntry } from '../../models/journal-entry.model';
import { JournalService } from '../../services/journal.service';

@Component({
  selector: 'app-journal-list',
  standalone: true,
  imports: [
    CommonModule
  ],
  templateUrl: './journal-list.component.html',
  styleUrl: './journal-list.component.css'
})
export class JournalListComponent implements OnInit {

  private journalService = inject(JournalService);
  private router = inject(Router);

  journals: JournalEntry[] = [];

  ngOnInit(): void {

    this.loadJournals();

  }

  loadJournals(): void {

    this.journalService.getAllJournals().subscribe({

      next: (data) => {

        this.journals = data;

      },

      error: (err) => {

        console.error(err);

      }

    });

  }

  openCreateJournal(): void {

    this.router.navigate(['/journals/new']);

  }

  openEditJournal(id: number): void {
    console.log('New journal clicked');
    this.router.navigate(['/journals/edit', id]);

  }

}
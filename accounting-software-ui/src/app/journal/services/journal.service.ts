import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { JournalEntry } from '../models/journal-entry.model';

@Injectable({
  providedIn: 'root'
})
export class JournalService {

    private http = inject(HttpClient);

    private apiUrl = 'http://localhost:8080/journals';

    getAllJournals(): Observable<JournalEntry[]> {

        return this.http.get<JournalEntry[]>(this.apiUrl);

    }

    getJournalById(id:number): Observable<JournalEntry>{

        return this.http.get<JournalEntry>(`${this.apiUrl}/${id}`);

    }

    createJournal(journal:JournalEntry): Observable<JournalEntry>{

        return this.http.post<JournalEntry>(this.apiUrl,journal);

    }

    updateJournal(id:number,journal:JournalEntry): Observable<JournalEntry>{

        return this.http.put<JournalEntry>(`${this.apiUrl}/${id}`,journal);

    }

}
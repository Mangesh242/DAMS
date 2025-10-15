import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { FlatOwner } from '../models/flat-owner.model';

@Injectable({
  providedIn: 'root'
})
export class FlatOwnerService {
  private apiUrl = 'http://localhost:8080/api/flat-owners';

  constructor(private http: HttpClient) { }

  getAllFlatOwners(): Observable<FlatOwner[]> {
    return this.http.get<FlatOwner[]>(this.apiUrl);
  }

  getFlatOwnerById(id: number): Observable<FlatOwner> {
    return this.http.get<FlatOwner>(`${this.apiUrl}/${id}`);
  }

  createFlatOwner(flatOwner: FlatOwner): Observable<FlatOwner> {
    return this.http.post<FlatOwner>(this.apiUrl, flatOwner);
  }

  updateFlatOwner(id: number, flatOwner: FlatOwner): Observable<FlatOwner> {
    return this.http.put<FlatOwner>(`${this.apiUrl}/${id}`, flatOwner);
  }

  deleteFlatOwner(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}

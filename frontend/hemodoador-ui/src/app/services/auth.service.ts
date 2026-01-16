import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({ providedIn: 'root' })
export class AuthService {

  private baseUrl = environment.apiUrl + environment.apiVersion + '/auth';

  constructor(private http: HttpClient) {}

  login(username: string, password: string): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/login`, { username, password }); }

  getToken() {
    if (typeof window !== 'undefined' && window.localStorage) { 
      return localStorage.getItem('token');
    } else {
      return null;
    }
  }

  logout() {
    localStorage.removeItem('token');
  }
}
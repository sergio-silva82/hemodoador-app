import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';

@Injectable({ providedIn: 'root' })
export class ApiService {

  private baseUrl = environment.apiUrl + environment.apiVersion;

  constructor(private http: HttpClient) { }

  importar(lista: any[]) {
    return this.http.post(`${this.baseUrl}/candidatos/importar`, lista);
  }

  uploadJson(file: File) {
    const form = new FormData();
    form.append('file', file);
    return this.http.post(`${this.baseUrl}/candidatos/upload`, form, { responseType: 'text' });
  }

  listarCandidatos() {
    return this.http.get(`${this.baseUrl}/candidatos`);
  }

  porEstado() {
    return this.http.get(`${this.baseUrl}/estatisticas/por-estado`);
  }

  imcFaixa() {
    return this.http.get(`${this.baseUrl}/estatisticas/imc-faixa`);
  }

  obesos() {
    return this.http.get(`${this.baseUrl}/estatisticas/obesos`);
  }

  idadeTipo() {
    return this.http.get(`${this.baseUrl}/estatisticas/idade-tipo-sanguineo`);
  }

  doadores() {
    return this.http.get(`${this.baseUrl}/estatisticas/doadores`);
  }
}
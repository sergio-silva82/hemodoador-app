import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from '../../services/api.service';
import { Chart, registerables } from 'chart.js';

Chart.register(...registerables);

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  constructor(private service: ApiService, private router: Router) { }

  ngOnInit() {
    this.carregarEstados();
    this.carregarImc();
    this.carregarObesos();
    this.carregarIdadeTipo();
    this.carregarDoadores();
  }

  voltar() { 
    this.router.navigate(['/upload']); 
  }

  carregarEstados() {
    this.service.porEstado().subscribe(dados => {
      new Chart('estadoChart', {
        type: 'bar',
        data: {
          labels: Object.keys(dados),
          datasets: [{
            label: 'Candidatos',
            data: Object.values(dados)
          }]
        }
      });
    });
  }

  carregarImc() {
    this.service.imcFaixa().subscribe(dados => {
      new Chart('imcChart', {
        type: 'line',
        data: {
          labels: Object.keys(dados),
          datasets: [{
            label: 'IMC Médio',
            data: Object.values(dados)
          }]
        }
      });
    });
  }

  carregarObesos() {
    this.service.obesos().subscribe(dados => {
      new Chart('obesosChart', {
        type: 'polarArea',
        data: {
          labels: Object.keys(dados),
          datasets: [{
            data: Object.values(dados)
          }]
        }
      });
    });
  }

  carregarIdadeTipo() {
    this.service.idadeTipo().subscribe(dados => {
      new Chart('idadeTipoChart', {
        type: 'line',
        data: {
          labels: Object.keys(dados),
          datasets: [{
            label: 'Média Idade p/TipoSanguíneo',
            data: Object.values(dados)
          }]
        }
      });
    });
  }

  carregarDoadores() {
    this.service.doadores().subscribe(dados => {
      new Chart('doadoresChart', {
        type: 'bar',
        data: {
          labels: Object.keys(dados),
          datasets: [{
            label: 'Doadores compatíveis',
            data: Object.values(dados)
          }]
        }
      });
    });
  }

}
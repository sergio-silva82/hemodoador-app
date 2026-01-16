import { Component } from '@angular/core';
// import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-upload',
  standalone: true,
  // imports: [CommonModule],
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.scss']
})

export class UploadComponent {
  file!: File;
  mensagem = '';
  labelTexto = 'Escolher arquivo';

  constructor(private api: ApiService, private router: Router) { }

  onFileSelected(event: any) {
    this.file = event.target.files[0];
    this.labelTexto = this.file.name;
  }

  enviar() {
    this.api.uploadJson(this.file).subscribe({
      next: () => {
        this.mensagem = 'Arquivo importado com sucesso!';
        this.router.navigate(['/dashboard']);
        alert(this.mensagem);
      },
      error: () => {
        this.mensagem = 'Erro ao importar';
        this.limparInput();
      }
    });
  }

  limparInput() {
    this.labelTexto = 'Escolher arquivo';
  }

  irParaDashboard() {
    this.router.navigate(['/dashboard']);
  }

}

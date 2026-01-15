package com.hemodoador.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hemodoador.dto.CandidatoDTO;
import com.hemodoador.service.CandidatoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/candidatos")
public class CandidatoController {

    private final CandidatoService candidatoService;

    @PostMapping("/importar")
    public ResponseEntity<?> importar(@RequestBody List<CandidatoDTO> lista) {
        candidatoService.importar(lista);
        return ResponseEntity.ok().build();
    }
    
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
        candidatoService.importarJson(file);
        return ResponseEntity.ok("Arquivo importado com sucesso");
    }

    @GetMapping
    public List<CandidatoDTO> listar() {
        return candidatoService.listarDTO();
    }
}

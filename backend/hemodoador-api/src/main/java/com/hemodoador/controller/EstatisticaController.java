package com.hemodoador.controller;

import org.springframework.web.bind.annotation.RestController;

import com.hemodoador.service.EstatisticaService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/estatisticas")
public class EstatisticaController {

    private final EstatisticaService service;

    @GetMapping("/por-estado")
    public Map<String, Long> porEstado() {
        return service.candidatosPorEstado();
    }

    @GetMapping("/imc-faixa")
    public Map<String, Double> imcFaixa() {
        return service.imcMedioPorFaixa();
    }

    @GetMapping("/obesos")
    public Map<String, Double> obesos() {
        return service.percentualObesos();
    }

    @GetMapping("/idade-tipo-sanguineo")
    public Map<String, Double> idadeTipo() {
        return service.idadeMediaPorTipoSanguineo();
    }

    @GetMapping("/doadores")
    public Map<String, Long> doadores() {
        return service.doadoresPorTipoReceptor();
    }
}
package com.hemodoador.service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.hemodoador.model.Candidato;
import com.hemodoador.repository.CandidatoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EstatisticaService {

    private final CandidatoRepository candidatoRepository;

    public Map<String, Long> candidatosPorEstado() {
        return candidatoRepository.countCandidatosPorEstado().stream()
                .collect(Collectors.toMap(
                        estado -> (String) estado[0],
                        quantidade -> (Long) quantidade[1]
                    ));
    }

    public Map<String, Double> imcMedioPorFaixa() {
        return candidatoRepository.findAll().stream()
            .collect(Collectors.groupingBy(c -> {
                int idade = c.getIdade();
                int faixa = (idade / 10) * 10;
                return faixa + " a " + (faixa + 9);
            }, Collectors.averagingDouble(Candidato::getImc)));
    }

    public Map<String, Double> percentualObesos() {
        List<Candidato> todos = candidatoRepository.findAll();

        return todos.stream().collect(Collectors.groupingBy( 
        	candidato -> candidato.getSexo().getCodigo(),
            Collectors.collectingAndThen(Collectors.toList(), lista -> {
                long obesos = lista.stream().filter(c -> c.getImc() > 30).count();
                return obesos * 100.0 / lista.size();
            })
        ));
    }

    public Map<String, Double> idadeMediaPorTipoSanguineo() {
        return candidatoRepository.findAll().stream()
            .collect(Collectors.groupingBy(
                candidato -> candidato.getTipoSanguineo().getCodigo(),
                Collectors.averagingInt(Candidato::getIdade)
            ));
    }

    public Map<String, Long> doadoresPorTipoReceptor() {
        List<Candidato> aptos = candidatoRepository.findAll().stream()
            .filter(c -> c.getIdade() >= 16 && c.getIdade() <= 69)
            .filter(c -> c.getPeso() > 50)
            .toList();

        Map<String, List<String>> compatibilidade = Map.of(
            "A+", List.of("A+","A-","O+","O-"),
            "A-", List.of("A-","O-"),
            "B+", List.of("B+","B-","O+","O-"),
            "B-", List.of("B-","O-"),
            "AB+", List.of("A+","A-","B+","B-","AB+","AB-","O+","O-"),
            "AB-", List.of("A-","B-","AB-","O-"),
            "O+", List.of("O+","O-"),
            "O-", List.of("O-")
        );

        Map<String, Long> resultado = new HashMap<>();

        for (String receptor : compatibilidade.keySet()) {
            long count = aptos.stream()
                .filter(c -> compatibilidade.get(receptor).contains(c.getTipoSanguineo().getCodigo()))
                .count();
            resultado.put(receptor, count);
        }

        return resultado;
    }
}
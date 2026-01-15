package com.hemodoador.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hemodoador.dto.CandidatoDTO;
import com.hemodoador.mapper.CandidatoMapper;
import com.hemodoador.model.Candidato;
import com.hemodoador.repository.CandidatoRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CandidatoService {
	
	private final CandidatoRepository candidatoRepository;
	private final CandidatoMapper candidatoMapper;
	private final ObjectMapper objectMapper;

    public void importar(List<CandidatoDTO> lista) {
        lista.forEach(dto -> {
            Candidato candidato = candidatoMapper.toEntity(dto);
            candidatoRepository.save(candidato);
        });
    }

    public List<CandidatoDTO> listarDTO() {
        return candidatoRepository.findAll()
                .stream()
                .map(candidatoMapper::toDto)
                .toList();
    }

    public List<Candidato> listar(){
    	return candidatoRepository.findAll();
    }
    
    public List<Object[]> quantidadeCandidatosPorEstado() {
    	return candidatoRepository.countCandidatosPorEstado();
    }

    public boolean podeDoar(Candidato c) {
        int idade = c.getIdade();
        return idade >= 16 && idade <= 69 && c.getPeso() > 50;
    }

	@Transactional
    public void importarJson(MultipartFile file) {

        validarArquivo(file);

        try (InputStream inputStream = file.getInputStream()) {

            List<CandidatoDTO> dtos = objectMapper.readValue(
                    inputStream,
                    new TypeReference<List<CandidatoDTO>>() {}
            );

            this.importar(dtos);

        } catch (IOException e) {
            throw new RuntimeException("Erro ao processar arquivo JSON", e);
        }
    }

    private void validarArquivo(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Arquivo vazio");
        }
        if (!file.getOriginalFilename().endsWith(".json")) {
            throw new IllegalArgumentException("Arquivo deve ser do tipo JSON");
        }
    }

}
package com.hemodoador.mapper;

import java.util.Set;
import java.util.HashSet;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import com.hemodoador.dto.CandidatoDTO;
import com.hemodoador.model.Candidato;
import com.hemodoador.model.Endereco;
import com.hemodoador.model.Telefone;
import com.hemodoador.model.enums.TipoTelefone;

@Mapper(componentModel = "spring")
public interface CandidatoMapper {

	@Mapping(source = "dataNascimento", target = "dataNasc", dateFormat = "dd/MM/yyyy")
    @Mapping(source = "enderecos", target = "cep", qualifiedByName = "cepFromEndereco")
    @Mapping(source = "enderecos", target = "endereco", qualifiedByName = "logradouroFromEndereco")
    @Mapping(source = "enderecos", target = "numero", qualifiedByName = "numeroFromEndereco")
    @Mapping(source = "enderecos", target = "bairro", qualifiedByName = "bairroFromEndereco")
    @Mapping(source = "enderecos", target = "cidade", qualifiedByName = "cidadeFromEndereco")
    @Mapping(source = "enderecos", target = "estado", qualifiedByName = "estadoFromEndereco")
    @Mapping(source = "telefones", target = "telefoneFixo", qualifiedByName = "telefoneFixo")
    @Mapping(source = "telefones", target = "celular", qualifiedByName = "celular")
    @Mapping(target = "sexo", expression = "java(candidato.getSexo() != null ? candidato.getSexo().getCodigo() : null)")
    @Mapping(target = "tipoSanguineo", expression = "java(candidato.getTipoSanguineo() != null ? candidato.getTipoSanguineo().getCodigo() : null)")
    CandidatoDTO toDto(Candidato candidato);

    @Mapping(source = "dataNasc", target = "dataNascimento", dateFormat = "dd/MM/yyyy")
    @Mapping(target = "sexo", expression = "java(com.hemodoador.model.enums.Sexo.fromCodigo(dto.getSexo()))")
    @Mapping(target = "tipoSanguineo", expression = "java(com.hemodoador.model.enums.TipoSanguineo.fromCodigo(dto.getTipoSanguineo()))")
    @Mapping(target = "enderecos", ignore = true)
    @Mapping(target = "telefones", ignore = true)
    Candidato toEntity(CandidatoDTO dto);


    @AfterMapping
    default void afterToEntity(CandidatoDTO dto, @MappingTarget Candidato candidato) {
        Endereco endereco = new Endereco();
        endereco.setCep(dto.getCep());
        endereco.setLogradouro(dto.getEndereco());
        endereco.setNumero(dto.getNumero());
        endereco.setBairro(dto.getBairro());
        endereco.setCidade(dto.getCidade());
        endereco.setEstado(dto.getEstado());
        endereco.getCandidatos().add(candidato);

        candidato.setEnderecos(Set.of(endereco));


        Set<Telefone> telefones = new HashSet<>();
        if (dto.getTelefoneFixo() != null) {
            Telefone t = new Telefone();
            t.setTipo(TipoTelefone.FIXO);
            t.setNumero(dto.getTelefoneFixo());
            t.setCandidato(candidato);
            telefones.add(t);
        }
        if (dto.getCelular() != null) {
            Telefone t = new Telefone();
            t.setTipo(TipoTelefone.CELULAR);
            t.setNumero(dto.getCelular());
            t.setCandidato(candidato);
            telefones.add(t);
        }

        candidato.setTelefones(telefones);
    }

    @Named("cepFromEndereco")
    default String cepFromEndereco(Set<Endereco> enderecos) {
        return enderecos == null || enderecos.isEmpty()
                ? null
                : enderecos.iterator().next().getCep();
    }

    @Named("logradouroFromEndereco")
    default String logradouroFromEndereco(Set<Endereco> enderecos) {
        return enderecos == null || enderecos.isEmpty()
                ? null
                : enderecos.iterator().next().getLogradouro();
    }

    @Named("numeroFromEndereco")
    default Integer numeroFromEndereco(Set<Endereco> enderecos) {
        return enderecos == null || enderecos.isEmpty()
                ? null
                : enderecos.iterator().next().getNumero();
    }

    @Named("bairroFromEndereco")
    default String bairroFromEndereco(Set<Endereco> enderecos) {
        return enderecos == null || enderecos.isEmpty()
                ? null
                : enderecos.iterator().next().getBairro();
    }

    @Named("cidadeFromEndereco")
    default String cidadeFromEndereco(Set<Endereco> enderecos) {
        return enderecos == null || enderecos.isEmpty()
                ? null
                : enderecos.iterator().next().getCidade();
    }

    @Named("estadoFromEndereco")
    default String estadoFromEndereco(Set<Endereco> enderecos) {
        return enderecos == null || enderecos.isEmpty()
                ? null
                : enderecos.iterator().next().getEstado();
    }

    @Named("telefoneFixo")
    default String telefoneFixo(Set<Telefone> telefones) {
        return telefones == null
                ? null
                : telefones.stream()
                    .filter(t -> t.getTipo() == TipoTelefone.FIXO)
                    .map(Telefone::getNumero)
                    .findFirst()
                    .orElse(null);
    }

    @Named("celular")
    default String celular(Set<Telefone> telefones) {
        return telefones == null
                ? null
                : telefones.stream()
                    .filter(t -> t.getTipo() == TipoTelefone.CELULAR)
                    .map(Telefone::getNumero)
                    .findFirst()
                    .orElse(null);
    }
}

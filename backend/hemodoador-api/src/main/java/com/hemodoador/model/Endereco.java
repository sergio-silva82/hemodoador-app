package com.hemodoador.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String cep;

    @Column
    private String logradouro;

    private Integer numero;

    @Column
    private String bairro;

    @Column
    private String cidade;

    @Column
    private String estado;

    @ManyToMany(mappedBy = "enderecos")
    private Set<Candidato> candidatos = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Endereco)) return false;
        Endereco e = (Endereco) o;
        return Objects.equals(cep, e.cep) &&
               Objects.equals(numero, e.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cep, numero);
    }

}

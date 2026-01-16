package com.hemodoador.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import com.hemodoador.model.converter.TipoSanguineoConverter;
import com.hemodoador.model.enums.Sexo;
import com.hemodoador.model.enums.TipoSanguineo;
import com.hemodoador.model.converter.SexoConverter;

@Getter
@Setter
@Entity
@Table(name = "candidato")
public class Candidato {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private String nome;

    @Column
    private String rg;

    @Column(name = "data_nasc")
    private LocalDate dataNascimento;

    @Column
    @Convert(converter = SexoConverter.class)
    private Sexo sexo;

    @Column
    private String mae;

    @Column
    private String pai;

    @Column
    private String email;

    @Column
    private Double altura;

    @Column
    private Double peso;

    @Column(name = "tipo_sanguineo", length = 3, nullable = false)
    @Convert(converter = TipoSanguineoConverter.class)
    private TipoSanguineo tipoSanguineo;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "candidato_endereco",
        joinColumns = @JoinColumn(name = "candidato_id"),
        inverseJoinColumns = @JoinColumn(name = "endereco_id")
    )
    private Set<Endereco> enderecos = new HashSet<>();
    
    @OneToMany(mappedBy = "candidato", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Telefone> telefones = new HashSet<>();

    
    public Double getImc() {
        return peso / (altura * altura);
    }

    public int getIdade() {
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }
}
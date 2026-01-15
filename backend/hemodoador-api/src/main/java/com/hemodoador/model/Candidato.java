package com.hemodoador.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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

    @Column(length = 11, nullable = false, unique = true)
    private String cpf;

    @Column(length = 200, nullable = false)
    private String nome;

    @Column(length = 20)
    private String rg;

    @Column(name = "data_nasc")
    private LocalDate dataNascimento;

    @Column
    @Convert(converter = SexoConverter.class)
    private Sexo sexo;

    @Column(length = 200)
    private String mae;

    @Column(length = 200)
    private String pai;

    @Column(length = 200)
    private String email;

    @Column
    private Double altura;

    @Column
    private Double peso;

    @Column(name = "tipo_sanguineo", length = 3, nullable = false)
    @Convert(converter = TipoSanguineoConverter.class)
    private TipoSanguineo tipoSanguineo;

    @ManyToMany
    @JoinTable(
        name = "candidato_endereco",
        joinColumns = @JoinColumn(name = "candidato_id"),
        inverseJoinColumns = @JoinColumn(name = "endereco_id")
    )
    private List<Endereco> enderecos = new ArrayList<Endereco>();
    
    public Double getImc() {
        return peso / (altura * altura);
    }

    public int getIdade() {
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }
}
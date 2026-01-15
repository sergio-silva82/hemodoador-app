package com.hemodoador.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import com.hemodoador.model.enums.TipoTelefone;

@Getter
@Setter
@Entity
@Table(name = "telefone")
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private TipoTelefone tipo;

    @Column
    private String numero;

    @ManyToOne
    @JoinColumn(name = "candidato_id", nullable = false)
    private Candidato candidato;
}

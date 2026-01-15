package com.hemodoador.model.enums;

import java.util.Arrays;

public enum TipoSanguineo {

    A_POS("A+"),
    A_NEG("A-"),
    B_POS("B+"),
    B_NEG("B-"),
    AB_POS("AB+"),
    AB_NEG("AB-"),
    O_POS("O+"),
    O_NEG("O-");

    private final String codigo;

    TipoSanguineo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public static TipoSanguineo fromCodigo(String codigo) {
        return Arrays.stream(values())
            .filter(t -> t.codigo.equalsIgnoreCase(codigo))
            .findFirst()
            .orElseThrow(() -> 
                new IllegalArgumentException("Tipo sanguíneo inválido: " + codigo));
    }
}
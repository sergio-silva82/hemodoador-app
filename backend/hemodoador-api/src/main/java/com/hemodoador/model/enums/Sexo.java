package com.hemodoador.model.enums;

import java.util.Arrays;

public enum Sexo {

    MASCULINO("Masculino"),
    FEMININO("Feminino");

    private final String codigo;

    Sexo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public static Sexo fromCodigo(String valor) {
        return Arrays.stream(values())
            .filter(s -> s.codigo.equalsIgnoreCase(valor))
            .findFirst()
            .orElseThrow(() ->
                new IllegalArgumentException("Sexo inválido: " + valor));
    }
}
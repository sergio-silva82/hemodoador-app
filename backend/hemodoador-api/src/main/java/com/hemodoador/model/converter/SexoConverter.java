package com.hemodoador.model.converter;

import com.hemodoador.model.enums.Sexo;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SexoConverter implements AttributeConverter<Sexo, String> {

    @Override
    public String convertToDatabaseColumn(Sexo sexo) {
        return sexo == null ? null : sexo.getCodigo();
    }

    @Override
    public Sexo convertToEntityAttribute(String valor) {
        return valor == null ? null : Sexo.fromCodigo(valor);
    }
}
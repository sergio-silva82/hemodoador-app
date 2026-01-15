package com.hemodoador.model.converter;

import com.hemodoador.model.enums.TipoSanguineo;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoSanguineoConverter implements AttributeConverter<TipoSanguineo, String> {

    @Override
    public String convertToDatabaseColumn(TipoSanguineo tipo) {
        return tipo == null ? null : tipo.getCodigo();
    }

    @Override
    public TipoSanguineo convertToEntityAttribute(String valor) {
        return valor == null ? null : TipoSanguineo.fromCodigo(valor);
    }
}
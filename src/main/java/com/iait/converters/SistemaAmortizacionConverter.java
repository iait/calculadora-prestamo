package com.iait.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.iait.enums.SistemaAmortizacionEnum;

@Converter(autoApply = true)
public class SistemaAmortizacionConverter 
        implements AttributeConverter<SistemaAmortizacionEnum, Character> {
    
    @Override
    public Character convertToDatabaseColumn(SistemaAmortizacionEnum attribute) {
        return attribute.getValue();
    }
    
    @Override
    public SistemaAmortizacionEnum convertToEntityAttribute(Character dbData) {
        return SistemaAmortizacionEnum.of(dbData);
    }
}

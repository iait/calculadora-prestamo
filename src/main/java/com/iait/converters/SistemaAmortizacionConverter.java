package com.iait.converters;

import javax.persistence.AttributeConverter;

import com.iait.enums.SistemaAmortizacionEnum;

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

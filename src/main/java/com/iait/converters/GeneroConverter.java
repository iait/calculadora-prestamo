package com.iait.converters;

import javax.persistence.AttributeConverter;

import com.iait.enums.GeneroEnum;

public class GeneroConverter implements AttributeConverter<GeneroEnum, String> {
    
    @Override
    public String convertToDatabaseColumn(GeneroEnum attribute) {
        return attribute.getGenero();
    }
    
    @Override
    public GeneroEnum convertToEntityAttribute(String dbData) {
        return GeneroEnum.of(dbData);
    }
}

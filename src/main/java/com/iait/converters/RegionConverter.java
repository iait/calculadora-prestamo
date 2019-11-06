package com.iait.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.iait.enums.RegionEnum;

@Converter(autoApply = true)
public class RegionConverter implements AttributeConverter<RegionEnum, String> {
    
    public String convertToDatabaseColumn(RegionEnum attribute) {
        return attribute.getRegion();
    }
    
    public RegionEnum convertToEntityAttribute(String dbData) {
        return RegionEnum.of(dbData);
    }
}

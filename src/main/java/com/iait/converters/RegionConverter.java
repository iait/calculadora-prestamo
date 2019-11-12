package com.iait.converters;

import javax.persistence.AttributeConverter;

import com.iait.enums.RegionEnum;

public class RegionConverter implements AttributeConverter<RegionEnum, String> {
    
    public String convertToDatabaseColumn(RegionEnum attribute) {
        return attribute.getRegion();
    }
    
    public RegionEnum convertToEntityAttribute(String dbData) {
        return RegionEnum.of(dbData);
    }
}

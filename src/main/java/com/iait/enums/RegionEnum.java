package com.iait.enums;

public enum RegionEnum {
    
    NOROESTE("NOA"),
    NORDESTE("NEA"),
    PAMPEANA("PAM"),
    CUYO("CUY"),
    PATAGONIA("PAT"),
    ;
    
    private String region;
    
    private RegionEnum(String region) {
        this.region = region;
    }
    
    public String getRegion() {
        return region;
    }
    
    public static RegionEnum of(String region) {
        if (region == null) {
            throw new IllegalArgumentException("La región no puede ser nula");
        } else if (region.trim().equalsIgnoreCase("NOA")) {
            return RegionEnum.NORDESTE;
        } else if (region.trim().equalsIgnoreCase("NEA")) {
            return RegionEnum.NORDESTE;
        } else if (region.trim().equalsIgnoreCase("PAM")) {
            return RegionEnum.PAMPEANA;
        } else if (region.trim().equalsIgnoreCase("CUY")) {
            return RegionEnum.CUYO;
        } else if (region.trim().equalsIgnoreCase("PAT")) {
            return RegionEnum.PATAGONIA;
        } else {
            throw new IllegalArgumentException(String.format("No se reconoce %s como una región", 
                    region));
        }
    }
}

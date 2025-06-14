package com.schedulecore.ufu.domains.models.enums;

public enum CampusEnum {

    FAEFI( "Campus Faculdade de Educação Física e Fisioterapia"),
    SANTA_MONICA("Campus Santa Mônica"),
    GLORIA("Campus Glória"),
    UMUARAMA("Campus Umuarama");
    private final String description;

    CampusEnum(String description) {
        this.description = description;
    }

    public static CampusEnum valueOfOrDefault(String name) {
        for (CampusEnum campus : CampusEnum.values()) {
            if (campus.name().equalsIgnoreCase(name)) {
                return campus;
            }
        }
        return null;
    }
}

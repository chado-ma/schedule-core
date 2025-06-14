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

    CampusEnum valueOfDescription(String description) {
        for (CampusEnum campus : CampusEnum.values()) {
            if (campus.description.equalsIgnoreCase(description)) {
                return campus;
            }
        }
        throw new IllegalArgumentException("No enum constant with description: " + description);
    }
}

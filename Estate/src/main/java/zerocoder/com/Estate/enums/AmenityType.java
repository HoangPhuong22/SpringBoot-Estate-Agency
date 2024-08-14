package zerocoder.com.Estate.enums;

import lombok.Getter;

@Getter
public enum AmenityType {
    INTERIOR("Nội thất"),
    EXTERIOR("Ngoại thất"),
    COMMON("Tiện ích chung"),
    OTHER("Khác");

    private final String description;

    AmenityType(String description) {
        this.description = description;
    }
}

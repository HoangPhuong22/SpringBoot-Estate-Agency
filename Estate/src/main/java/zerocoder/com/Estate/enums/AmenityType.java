package zerocoder.com.Estate.enums;

public enum AmenityType {
    INTERIOR("Nội thất"),
    EXTERIOR("Ngoại thất"),
    COMMON("Tiện ích chung"),
    OTHER("Khác");

    private final String description;

    AmenityType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

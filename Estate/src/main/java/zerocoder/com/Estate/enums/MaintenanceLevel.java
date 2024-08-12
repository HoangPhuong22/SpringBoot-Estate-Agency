package zerocoder.com.Estate.enums;

public enum MaintenanceLevel {
    LOW("Thấp"),
    MEDIUM("Trung bình"),
    HIGH("Cao");

    private final String description;

    MaintenanceLevel(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
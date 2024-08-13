package zerocoder.com.Estate.enums;

import lombok.Getter;

@Getter
public enum MaintenanceStatus {
    PENDING("Chờ xử lý"),
    IN_PROGRESS("Đang xử lý"),
    COMPLETED("Hoàn thành"),
    CANCELLED("Đã hủy");

    private final String description;

    MaintenanceStatus(String description) {
        this.description = description;
    }
}
package zerocoder.com.Estate.enums;

import lombok.Getter;

@Getter
public enum CustomerStatus {
    PENDING("Chờ xác nhận"),
    ASSIGNED("Đã phân công"),
    DONE("Đã hoàn thành"),
    ;
    private final String description;

    CustomerStatus(String description) {
        this.description = description;
    }
}

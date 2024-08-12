package zerocoder.com.Estate.enums;

import lombok.Getter;

@Getter
public enum PropertyStatus {
    AVAILABLE("Trống"),
    RENTED("Đã cho thuê"),
    SOLD("Đã bán"),
    UNDER_REPAIR("Đang sửa chữa");

    private final String description;

    PropertyStatus(String description) {
        this.description = description;
    }

}

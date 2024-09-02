package zerocoder.com.Estate.enums;

import lombok.Getter;

@Getter
public enum PropertyStatus {
    AVAILABLE("Trống"),
    RENTED("Đã cho thuê"),
    SOLD("Đã bán"),
    ;

    private final String description;

    PropertyStatus(String description) {
        this.description = description;
    }

}

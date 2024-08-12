package zerocoder.com.Estate.enums;

import lombok.Getter;

@Getter
public enum PropertyDirection {
    EAST("Hướng Đông"),
    NORTH("Hướng Bắc"),
    SOUTH("Hướng Nam"),
    WEST("Hướng Tây"),
    ;
    private final String description;
    PropertyDirection(String s) {
        this.description = s;
    }
}

package zerocoder.com.Estate.enums;

import lombok.Getter;

@Getter
public enum DirectionProperty {
    EAST("Hướng Đông"),
    NORTH("Hướng Bắc"),
    SOUTH("Hướng Nam"),
    WEST("Hướng Tây"),
    ;
    private final String description;
    DirectionProperty(String s) {
        this.description = s;
    }
}

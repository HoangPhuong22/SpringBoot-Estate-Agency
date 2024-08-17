package zerocoder.com.Estate.enums;

import lombok.Getter;

@Getter
public enum Gender {
    MALE("Nam"),
    FEMALE("Nữ"),
    OTHER("Khác"),
    ;
    private final String description;
    Gender(String description) {
        this.description = description;
    }
}

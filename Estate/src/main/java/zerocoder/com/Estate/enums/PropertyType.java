package zerocoder.com.Estate.enums;

import lombok.Getter;


@Getter
public enum PropertyType {
    APARTMENT("Căn hộ"),
    HOUSE("Nhà"),
    VILLA("Biệt thự"),
    ;

    private final String description;

    PropertyType(String s) {
        this.description = s;
    }
}

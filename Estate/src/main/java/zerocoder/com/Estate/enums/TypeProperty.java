package zerocoder.com.Estate.enums;

import lombok.Getter;

@Getter
public enum TypeProperty {
    APARTMENT("Căn hộ"),
    HOUSE("Nhà"),
    VILLA("Biệt thự"),
    ;
    private final String description;
    TypeProperty(String s) {
        this.description = s;
    }
}

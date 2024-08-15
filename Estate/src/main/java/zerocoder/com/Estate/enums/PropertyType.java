package zerocoder.com.Estate.enums;

import lombok.Getter;


@Getter
public enum PropertyType {
    APARTMENT("Căn hộ", "CH"),
    HOUSE("Nhà riêng","NR"),
    VILLA("Biệt thự", "BT"),
    ;

    private final String description;
    private final String code;

    PropertyType(String desc, String code) {
        this.description = desc;
        this.code = code;
    }
}

package zerocoder.com.Estate.enums;

import lombok.Getter;

@Getter
public enum Role {
    ADMIN ("ADMIN"),
    EMPLOYEE ("EMPLOYEE"),
    CUSTOMER ("CUSTOMER");

    private final String value;

    Role(String value) {
        this.value = value;
    }
}

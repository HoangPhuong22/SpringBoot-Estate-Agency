package zerocoder.com.Estate.enums;

import lombok.Getter;

@Getter
public enum InspectionStatus {
    PASSED("Đã đạt"),
    FAILED("Không đạt")
    ;

    private final String description;

    InspectionStatus(String description) {
        this.description = description;
    }
}

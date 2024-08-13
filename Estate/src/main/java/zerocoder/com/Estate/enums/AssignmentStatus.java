package zerocoder.com.Estate.enums;

import lombok.Getter;

@Getter
public enum AssignmentStatus {
    MANAGING("Đang quản lý"),
    ENDED("Đã kết thúc"),
    ;
    private final String description;

    AssignmentStatus(String description) {
        this.description = description;
    }
}

package zerocoder.com.Estate.enums;

import lombok.Getter;

@Getter
public enum ActivityType {
    MEETING_CUSTOMER("Gặp gỡ khách hàng"),
    CONTRACT_DISCUSSION("Thảo luận hợp đồng"),
    PROPERTY_SHOWING("Dẫn khách xem nhà"),
    NEGOTIATION("Đàm phán"),
    CLOSING_DEAL("Chốt hợp đồng"),
    OTHER("Khác");

    private final String description;

    ActivityType(String description) {
        this.description = description;
    }
}

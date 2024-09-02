package zerocoder.com.Estate.enums;

import lombok.Getter;

@Getter
public enum ContractStatus {
    UPCOMING("Hợp đồng sắp tới"),      // Hợp đồng sắp tới
    ACTIVE("Hợp đồng đang hoạt động"), // Hợp đồng đang hoạt động
    EXPIRED("Hợp đồng đã hết hạn");     // Hợp đồng đã hết hạn

    private final String description;

    ContractStatus(String description) {
        this.description = description;
    }

}

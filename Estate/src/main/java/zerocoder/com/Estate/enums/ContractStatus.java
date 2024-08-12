package zerocoder.com.Estate.enums;

public enum ContractStatus {
    IN_PROGRESS("Đang thực hiện"),
    COMPLETED("Đã kết thúc"),
    CANCELLED("Đã hủy");

    private final String description;

    ContractStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

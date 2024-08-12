package zerocoder.com.Estate.enums;

public enum ContractType {
    SALE("Mua bán"),
    RENT("Cho thuê");

    private final String description;

    ContractType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

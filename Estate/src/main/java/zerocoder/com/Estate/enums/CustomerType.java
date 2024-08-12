package zerocoder.com.Estate.enums;

public enum CustomerType {
    INDIVIDUAL("Cá nhân"),
    BUSINESS("Doanh nghiệp"),

    OTHER("Khác"),
    ;
    private final String description;

    CustomerType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

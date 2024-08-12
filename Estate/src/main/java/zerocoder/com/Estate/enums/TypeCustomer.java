package zerocoder.com.Estate.enums;

public enum TypeCustomer {
    INDIVIDUAL("Cá nhân"),
    BUSINESS("Doanh nghiệp"),

    OTHER("Khác"),
    ;
    private final String description;

    TypeCustomer(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

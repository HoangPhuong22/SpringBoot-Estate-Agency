package zerocoder.com.Estate.exception;

import lombok.Getter;

@Getter
public class ContractValidException extends RuntimeException {

    private final String field;

    public ContractValidException(String message, String field) {
        super(message);
        this.field = field;
    }
}

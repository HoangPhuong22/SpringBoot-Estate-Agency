package zerocoder.com.Estate.exception;

import lombok.Getter;

@Getter
public class UniqueException extends RuntimeException{
    private final String field;

    public UniqueException(String message, String field) {
        super(message);
        this.field = field;
    }

    public UniqueException(String message, Throwable cause, String field) {
        super(message, cause);
        this.field = field;
    }
}

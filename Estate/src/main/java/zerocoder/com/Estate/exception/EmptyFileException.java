package zerocoder.com.Estate.exception;

import lombok.Getter;

@Getter
public class EmptyFileException extends RuntimeException{
    private final String field;

    public EmptyFileException(String message, String field) {
        super(message);
        this.field = field;
    }

    public EmptyFileException(String message, Throwable cause, String field) {
        super(message, cause);
        this.field = field;
    }
}

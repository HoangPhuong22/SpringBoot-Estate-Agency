package zerocoder.com.Estate.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import zerocoder.com.Estate.dto.response.ResponseData;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseData<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), "Validation error", errors);
    }

    @ExceptionHandler({UniqueException.class, EmptyFileException.class, ContractValidException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseData<?> handleUniqueException(Exception e) {
        Map<String, String> errors = new HashMap<>();
        String message;
        if(e instanceof ContractValidException) {
            message = "Contract valid exception";
            errors.put(((ContractValidException) e).getField(), e.getMessage());
        } else if(e instanceof EmptyFileException) {
            message = "Empty file exception";
            errors.put(((EmptyFileException) e).getField(), e.getMessage());
        } else {
            message = "Unique exception";
            errors.put(((UniqueException) e).getField(), e.getMessage());
        }
        return new ResponseData<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), message ,errors);
    }


    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseData<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("Error exception global handler: ", e.getMessage());
        return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }
}

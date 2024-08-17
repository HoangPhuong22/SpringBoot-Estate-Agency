package zerocoder.com.Estate.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import zerocoder.com.Estate.validator.impl.PhoneValidator;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface PhoneValid {
    String message() default "Số điện thoại không hợp lệ";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

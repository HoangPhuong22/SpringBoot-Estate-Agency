package zerocoder.com.Estate.validator;

import jakarta.validation.Constraint;
import zerocoder.com.Estate.validator.impl.EnumNamePatternValidator;
import zerocoder.com.Estate.validator.impl.IdNumberValidator;

import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IdNumberValidator.class)
public @interface IdNumberValid {
    String message() default "Số CMND không hợp lệ";
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};
}

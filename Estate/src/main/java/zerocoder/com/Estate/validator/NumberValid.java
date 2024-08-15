package zerocoder.com.Estate.validator;

import jakarta.validation.Constraint;
import zerocoder.com.Estate.validator.impl.EnumNamePatternValidator;
import zerocoder.com.Estate.validator.impl.NumberValidator;

import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NumberValidator.class)
public @interface NumberValid {
    String message() default "must be a number";
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};
}

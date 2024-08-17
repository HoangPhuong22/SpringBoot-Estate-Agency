package zerocoder.com.Estate.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import zerocoder.com.Estate.validator.impl.AgeValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AgeValidator.class)
@Target({ ElementType.FIELD, ElementType.METHOD})
public @interface Age {
    int value();
    String message() default "Tuổi phải lớn hơn hoặc bằng {value}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

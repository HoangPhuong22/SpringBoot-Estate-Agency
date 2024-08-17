package zerocoder.com.Estate.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import zerocoder.com.Estate.validator.impl.PasswordValidator;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
public @interface PasswordValid {

    int min();

    String message() default "Mật khẩu phải chứa ít nhất {min} ký tự, bao gồm chữ hoa, chữ thường, số và ký tự đặc biệt";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

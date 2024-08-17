package zerocoder.com.Estate.validator.impl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import zerocoder.com.Estate.validator.IdNumberValid;

public class IdNumberValidator implements ConstraintValidator<IdNumberValid, String> {
    @Override
    public void initialize(IdNumberValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s != null && s.matches("^\\d{12}$");
    }
}

package zerocoder.com.Estate.validator.impl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import zerocoder.com.Estate.validator.PhoneValid;

public class PhoneValidator implements ConstraintValidator<PhoneValid, String> {
    @Override
    public void initialize(PhoneValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s != null && s.matches("0[0-9]{9,10}");
    }
}

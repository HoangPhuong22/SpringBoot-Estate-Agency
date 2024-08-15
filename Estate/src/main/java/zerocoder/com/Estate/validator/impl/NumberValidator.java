package zerocoder.com.Estate.validator.impl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import zerocoder.com.Estate.validator.NumberValid;

public class NumberValidator implements ConstraintValidator<NumberValid, String> {

    @Override
    public void initialize(NumberValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

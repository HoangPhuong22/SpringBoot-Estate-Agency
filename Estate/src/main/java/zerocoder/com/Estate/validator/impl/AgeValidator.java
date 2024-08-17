package zerocoder.com.Estate.validator.impl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import zerocoder.com.Estate.validator.Age;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class AgeValidator implements ConstraintValidator<Age, LocalDate> {
    private int minAge;
    @Override
    public void initialize(Age age) {
       this.minAge = age.value();
    }

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        if (localDate == null) {
            return false;
        }
        return ChronoUnit.YEARS.between(localDate, LocalDate.now()) >= minAge;
    }
}

package zerocoder.com.Estate.validator.impl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import zerocoder.com.Estate.validator.EnumNamePattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EnumNamePatternValidator implements ConstraintValidator<EnumNamePattern, String> {

    private Pattern pattern;

    @Override
    public void initialize(EnumNamePattern annotation) {
        pattern = Pattern.compile(annotation.regexp());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        Matcher m = pattern.matcher(value);
        return m.matches();
    }
}

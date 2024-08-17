package zerocoder.com.Estate.validator.impl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import zerocoder.com.Estate.validator.PasswordValid;

public class PasswordValidator implements ConstraintValidator<PasswordValid, String> {
    private int min;
    @Override
    public void initialize(PasswordValid constraintAnnotation) {
        this.min = constraintAnnotation.min();
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        if (password == null) {
            return false;
        }
        // Kiểm tra độ dài
        if (password.length() < min) {
            return false;
        }
        // Kiểm tra kí tự in hoa
        if (!password.matches(".*[A-Z].*")) {
            return false;
        }
        // Kiểm tra chữ số
        if (!password.matches(".*[0-9].*")) {
            return false;
        }
        // Kiểm tra kí tự đặc biệt
        return password.matches(".*[!@#$%^&*(),.?\":{}|<>].*");
    }
}

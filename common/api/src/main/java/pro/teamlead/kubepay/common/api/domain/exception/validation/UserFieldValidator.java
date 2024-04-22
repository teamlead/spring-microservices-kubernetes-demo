package pro.teamlead.kubepay.common.api.domain.exception.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class UserFieldValidator implements ConstraintValidator<ValidUserField, String> {

    private static final Pattern VALID_PATTERN = Pattern.compile("^[a-zA-Z0-9-_]+$");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Let @NotNull or similar annotations handle null checks
        }
        return VALID_PATTERN.matcher(value).matches();
    }
}

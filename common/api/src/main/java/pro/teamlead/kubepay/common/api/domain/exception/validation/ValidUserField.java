 package pro.teamlead.kubepay.common.api.domain.exception.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserFieldValidator.class)
public @interface ValidUserField {

    String message() default "Should only contain letters (a-Z), numbers, hyphens, and underscores.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

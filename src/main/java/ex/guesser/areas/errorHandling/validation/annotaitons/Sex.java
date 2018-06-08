package ex.guesser.areas.errorHandling.validation.annotaitons;

import ex.guesser.areas.errorHandling.validation.validators.SexValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = SexValidator.class)
public @interface Sex {

    String message() default "Invalid sex";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

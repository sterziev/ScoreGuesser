package ex.guesser.areas.errorHandling.validation.annotaitons;

import ex.guesser.areas.errorHandling.validation.validators.CustomMinForStrValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = CustomMinForStrValidator.class)
public @interface CustomMinForStr {

    String message() default "Invalid input, less than threshold";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String value() default "0";
}

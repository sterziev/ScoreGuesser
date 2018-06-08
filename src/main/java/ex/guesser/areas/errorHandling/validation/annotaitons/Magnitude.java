package ex.guesser.areas.errorHandling.validation.annotaitons;

import ex.guesser.areas.errorHandling.validation.validators.VirusMagnitudeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = VirusMagnitudeValidator.class)
public @interface Magnitude {

    String message() default "Invalid magnitude";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

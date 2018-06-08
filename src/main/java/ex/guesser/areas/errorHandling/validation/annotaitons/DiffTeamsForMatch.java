package ex.guesser.areas.errorHandling.validation.annotaitons;

import ex.guesser.areas.errorHandling.validation.validators.DiffTeamsForMatchValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;


@Target({TYPE,ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DiffTeamsForMatchValidator.class)
@Documented
public @interface DiffTeamsForMatch {
    String message() default "The teams cannot be the same";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}